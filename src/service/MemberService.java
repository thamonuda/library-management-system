package service;

import java.sql.SQLException;


import dao.MemberDao;
import dto.MemberDto;
import entity.MemberEntity;

public class MemberService {
    
    private MemberDao memberDAO = new MemberDao();

    public void addMember(MemberDto memberDto) throws ClassNotFoundException, SQLException {
        MemberEntity memberEntity = new MemberEntity(
            memberDto.getId(),
            memberDto.getName(),
            memberDto.getDob(),
            memberDto.getAddress()
        );
        
        memberDAO.saveMember(memberEntity);
    }
}