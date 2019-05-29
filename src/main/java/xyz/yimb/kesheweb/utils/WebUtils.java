package xyz.yimb.kesheweb.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.DigestUtils;
import xyz.yimb.kesheweb.entity.Student;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WebUtils {


    public static String getMD5(String password){
        String digest = DigestUtils.md5DigestAsHex(password.getBytes());
        return digest;
    }

    public static  ArrayList<Student> getStudentList(InputStream is, String filename) {
        ArrayList<Student> list=new ArrayList<>();
        Workbook wb=null;
        try{
            if (filename.endsWith(".xls")){//HSSFWorkbook，对应xls格式的Excel文档
                System.out.println(03);
                wb=new HSSFWorkbook(is);
            }else if (filename.endsWith(".xlsx")){//XSSFWorkbook，对应xlsx格式的Excel文档；
                System.out.println(07);
                wb= new XSSFWorkbook(is);
            }
            Sheet sheet = wb.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 0; i <lastRowNum ; i++) {
                if (i==0){
                    continue;
                }
                Row row = sheet.getRow(i);
                Student student=new Student();
                Cell cell1 = row.getCell(1);
                String c1 = cell1.toString().trim();
                student.setAccount(c1);
                Cell cell2 = row.getCell(2);
                String c2 = cell2.toString().trim();
                student.setName(c2);
                student.setPassword(WebUtils.getMD5("1"));
                list.add(student);
            }
        }catch (Exception e){
            System.out.println("异常！");
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static XSSFWorkbook getHSSFWorkbook(List<Student> list) {
        XSSFWorkbook wb=new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        XSSFRow r = sheet.createRow(0);
        r.createCell(0).setCellValue("序号");
        r.createCell(1).setCellValue("学号");
        r.createCell(2).setCellValue("姓名");
        r.createCell(3).setCellValue("分数");
        for (int i = 0; i < list.size(); i++) {
            XSSFRow row = sheet.createRow(i+1);
            Student s = list.get(i);
            row.createCell(0).setCellValue((i+1)+"");
            row.createCell(1).setCellValue(s.getAccount());
            row.createCell(2).setCellValue(s.getName());
            row.createCell(3).setCellValue(s.getGrade());
        }
        return wb;
    }
}
