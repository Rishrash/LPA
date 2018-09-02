package com.cg.lpa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.cg.lpa.bean.LoanProgramOfferedBean;
import com.cg.lpa.bean.UserBean;
import com.cg.lpa.dbutil.DBUtil;

public class TestQueries {
	static UserBean user = new UserBean();
	static Connection conn = null;
	static PreparedStatement pstmt = null;
	static Statement stmt = null;
	static ResultSet rs = null;

	public static void main(String[] args) {

		try {
			ArrayList<LoanProgramOfferedBean> loanProgramList = new ArrayList();
			conn = DBUtil.establishConnection();
			stmt = conn.createStatement();
			ResultSet rst;
			rst = stmt.executeQuery(IQueryMapper.GET_LOAN_PROGRAMS_OFFERED);

			while (rst.next()) {
				LoanProgramOfferedBean loanProgram = new LoanProgramOfferedBean(
						rst.getString(1), rst.getString(2), rst.getString(3),
						rst.getInt(4), rst.getDouble(5), rst.getDouble(6),
						rst.getDouble(7), rst.getString(8));
				loanProgramList.add(loanProgram);
				for (LoanProgramOfferedBean loanProgram1 : loanProgramList) {
					System.out.println(loanProgram1);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
