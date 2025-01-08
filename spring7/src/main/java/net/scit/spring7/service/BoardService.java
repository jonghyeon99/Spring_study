package net.scit.spring7.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.dto.BoardDTO;
import net.scit.spring7.entity.BoardEntity;
import net.scit.spring7.repository.BoardRepository;
import net.scit.spring7.util.FileService;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
	private final BoardRepository boardRepository;
	
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	/**
	 * 1) 단순조회: 게시글 전체 목록 조회
	 * 2) 검색조회: 쿼리 메소드 사용
	 * 
	 * @param searchWord :
	 * @param searchItem : 
	 * 
	 * @return 
	 */
	public List<BoardDTO> selectAll(String searchItem, String searchWord) {
		// 2) 검색조회
		List<BoardEntity> temp = null;
		
		switch (searchItem) {
			case "boardTitle":
				temp = boardRepository.findByBoardTitleContains(searchWord, Sort.by(Sort.Direction.DESC, "createDate"));
				break;
			case "boardWriter":
				temp = boardRepository.findByBoardWriterContains(searchWord, Sort.by(Sort.Direction.DESC, "createDate"));
				break;
			case "boardContent":
				temp = boardRepository.findByBoardContentContains(searchWord, Sort.by(Sort.Direction.DESC, "createDate"));
				break;				
		}

		
		// 1) 단순조회
		// List<BoardEntity> temp = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));

		List<BoardDTO> list = new ArrayList<>();

		temp.forEach((entity) -> list.add(BoardDTO.toDTO(entity)));

		return list;
	}

	/**
	 * 전달받은 BoardDTO를 DB에 저장
	 * 첨부파일 여부에 따라 DB의 두 컬럼값을 수정
	 * @param boardDTO
	 */
	public void insertBoard(BoardDTO boardDTO) {
		MultipartFile uploadFile = boardDTO.getUploadFile();
		String savedFileName = null;
		String originalFileName = null;
		
		if (!uploadFile.isEmpty()) {
			savedFileName = FileService.saveFile(boardDTO.getUploadFile(), uploadPath);
			originalFileName = boardDTO.getUploadFile().getOriginalFilename();
		}
		
		boardDTO.setSavedFileName(savedFileName);
		boardDTO.setOriginalFileName(originalFileName);
		
		
		BoardEntity entity = BoardEntity.toEntity(boardDTO);
		
		boardRepository.save(entity);
	}

	/**
	 * boardSeq에 해당하는 글을 조회
	 * @param boardSeq
	 * @return
	 */
	public BoardDTO selectOne(Long boardSeq) {
		Optional<BoardEntity> temp = boardRepository.findById(boardSeq);

		if(!temp.isPresent()) return null;

		return BoardDTO.toDTO(temp.get());
	}

	/**
	 * boardSeq레코드에 대한 조회수 증가
	 * 
	 * 1) 조회: findById(boardSEQ)
	 * 2) hitcount를 get한후 +1 ==> set
	 * 
	 * @param boardSeq
	 */
	@Transactional
	public void incrementHitcount(Long boardSeq) {
		Optional<BoardEntity> temp = boardRepository.findById(boardSeq);

		if(!temp.isPresent()) return ;
		BoardEntity entity = temp.get();

		int hitcount = entity.getHitCount() + 1;
		entity.setHitCount(hitcount);
	}

	/**
	 * DB에 삭제요청
	 */
	@Transactional
	public void deleteOne(Long boardSeq) {
		Optional<BoardEntity> temp = boardRepository.findById(boardSeq);

		if(!temp.isPresent()) return ;
		
		String savedFileName = temp.get().getSavedFileName();
		
		if (savedFileName != null) {
			String fullPath = uploadPath + "/" + savedFileName;
			FileService.deleteFile(fullPath);
		}
		
		boardRepository.deleteById(boardSeq);
		
	}

	/**
	 * DB에 수정처리
	 * @param boardDTO
	 */
	@Transactional
	public void updateBoard(BoardDTO boardDTO) {
		
		// 첨부파일이 있는지 확인
		MultipartFile file = boardDTO.getUploadFile();
		
		String newFile = !file.isEmpty() ? file.getOriginalFilename() : null;
		
		// 1) 수정하려는 데이터가 있는지 확인
		Optional<BoardEntity> temp = boardRepository.findById(boardDTO.getBoardSeq());

		if(!temp.isPresent()) return;
		
		// 2) 있으면 dto -> entity로 변환
		BoardEntity entity = temp.get();
		
		// 기존 파일이 DB에 저장되어 있는지 확인
		String oldFile = entity.getSavedFileName();
		
		// 하드디스크에서는 기존 파일이 있고 업로드한 파일도 있다면 기존 파일 삭제, 업로드한 파일 저장
		String savedFileName = null;
		
		if (newFile != null) {
			savedFileName = FileService.saveFile(file, uploadPath);
			
			if (oldFile != null) {
				String fullPath = uploadPath + "/" + oldFile;
				FileService.deleteFile(fullPath);
			}
		} 
		
		entity.setSavedFileName(savedFileName);
		entity.setOriginalFileName(newFile);
		entity.setBoardTitle(boardDTO.getBoardTitle());
		entity.setBoardContent(boardDTO.getBoardContent());
	}

	/*
	 * file명이 들어있는 2개의 컬럼의 값을 null로!
	 */
	@Transactional
	public void deleteFile(Long boardSeq) {
		Optional<BoardEntity> temp = boardRepository.findById(boardSeq);
		
		if (temp.isPresent()) {
			BoardEntity entity = temp.get();
			entity.setOriginalFileName(null);
			entity.setSavedFileName(null);
		}
		
	}
}






