package service;

import java.sql.SQLException;
import java.util.List;


import dao.AllMemberDao;

import dto.MemberDto;

public class AllMemberService {
    
        private AllMemberDao allMemberDao = new AllMemberDao();

    public List<MemberDto> getAllMembers() throws SQLException, ClassNotFoundException {

        return allMemberDao.getAllMembers();
    }

    public void deleteMember(String id) throws ClassNotFoundException, SQLException {
        allMemberDao.deleteMember(id);
    }
}
