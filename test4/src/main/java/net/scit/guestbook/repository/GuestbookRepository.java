package net.scit.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.guestbook.entity.GuestbookEntity;

public interface GuestbookRepository extends JpaRepository<GuestbookEntity, Integer> {

	void deleteBySeqnoAndGuestPwd(Integer seqno, String guestPwd);

}
