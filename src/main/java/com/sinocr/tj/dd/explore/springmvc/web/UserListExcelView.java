package com.sinocr.tj.dd.explore.springmvc.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.sinocr.tj.dd.explore.springmvc.domain.User;

public class UserListExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "inline;filename=" + new String("用户列表.xlsx".getBytes(), "Iso8859-1"));
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) model.get("userList");
		Sheet sheet = workbook.createSheet("users");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("账号");
		header.createCell(1).setCellValue("姓名");
		header.createCell(2).setCellValue("生日");
		int rowNum = 1;
		for (User user : users) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(user.getUserName());
			row.createCell(1).setCellValue(user.getRealName());
			String createDate = DateFormatUtils.format(user.getBirthday(), "yyyy-MM-dd");
			row.createCell(2).setCellValue(createDate);
		}
	}

}
