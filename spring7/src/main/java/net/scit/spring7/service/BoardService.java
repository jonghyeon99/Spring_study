package net.scit.spring7.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.dto.BoardDTO;
import net.scit.spring7.entity.BoardEntity;
import net.scit.spring7.repository.BoardRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
	private final BoardRepository boardRepository;

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
	 * @param boardDTO
	 */
	public void insertBoard(BoardDTO boardDTO) {
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
		boardRepository.deleteById(boardSeq);
	}

	/**
	 * DB에 수정처리
	 * @param boardDTO
	 */
	@Transactional
	public void updateBoard(BoardDTO boardDTO) {
		// 1) 수정하려는 데이터가 있는지 확인
		Optional<BoardEntity> temp = boardRepository.findById(boardDTO.getBoardSeq());

		if(!temp.isPresent()) return;
		
		// 2) 있으면 dto -> entity로 변환
		BoardEntity entity = temp.get();
		
		entity.setBoardTitle(boardDTO.getBoardTitle());
		entity.setBoardContent(boardDTO.getBoardContent());
		// entity.setUpdateDate(LocalDateTime.now());
	}
}






