package net.scit.spring7.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.dto.ReplyDTO;
import net.scit.spring7.entity.BoardEntity;
import net.scit.spring7.entity.ReplyEntity;
import net.scit.spring7.repository.BoardRepository;
import net.scit.spring7.repository.ReplyRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyService {
	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;

	/**
	 * 전달받은 값을 entity로 수정한 후에 DB에 저장
	 * @param replyDTO
	 */
	public void replyInsert(ReplyDTO replyDTO) {
		// 1) 부모글이 있는지 조회
		Optional<BoardEntity> temp = boardRepository.findById(replyDTO.getBoardSeq());

		if(!temp.isPresent()) return;

		// 2) 부모글을 꺼냄
		BoardEntity boardEntity = temp.get();

		// 3) 두개를 전달해서 entity를 반환
		ReplyEntity replyEntity = ReplyEntity.toEntity(replyDTO, boardEntity);

		// 3) DB에 저장(save)
		replyRepository.save(replyEntity);

	}

	/**
	 * boardSeq 에 해당하는 댓글 전체 조회
	 * @param boardSeq
	 * @return
	 */
	public List<ReplyDTO> replyAll(Long boardSeq) {
		// 1) 부모글이 있는지 조회
		Optional<BoardEntity> temp = boardRepository.findById(boardSeq);

		// 2) 댓글 조회를 위한 Query Method
		List<ReplyEntity> entityList = 
				replyRepository.findAllByBoardEntity(temp, Sort.by(Sort.Direction.DESC, "replySeq"));

		// 3) List<ReplyDTO>를 선언
		List<ReplyDTO> list = new ArrayList<>();
		
		// 4) Entity --> DTO
		entityList.forEach((entity) -> list.add(ReplyDTO.toDTO(entity, boardSeq)));
		
		return list;
	}

	public void replyDelete(Long replySeq) {
		Optional<ReplyEntity> temp =  replyRepository.findById(replySeq);
		
		if(!temp.isPresent()) return;
		replyRepository.deleteById(replySeq);
		
	}

	/*
	 * 댓글 조회
	 */
	public ReplyDTO replySelectOne(Long replySeq) {
		Optional<ReplyEntity> temp =  replyRepository.findById(replySeq);
		
		if(!temp.isPresent()) return null;
		
		ReplyEntity entity = temp.get();
		
		ReplyDTO replyDTO = ReplyDTO.toDTO(entity, entity.getBoardEntity().getBoardSeq());
		
		return replyDTO;
	}


}
