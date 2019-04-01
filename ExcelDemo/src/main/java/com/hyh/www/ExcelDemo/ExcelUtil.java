package com.hyh.www.ExcelDemo;

//��excel���ж�ȡ����

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//��ȡWorkbook
public class ExcelUtil {
	public static int getCellNum() {
		return cellNum;
	}

	public static int getRowNum() {
		return rowNum;
	}

	public static String[] getExcelCell() {
		return excelCell;
	}

	private static final String EXCEL_XLS="xls";
	private static final String EXCEL_XLSX="xlsx";
	private static String[] excelCell=new String[3000];     //���浥Ԫ������
	private static int cellNum;                             //����
	private static int rowNum;                              //����
	
	public static Workbook getWorkbook(InputStream in,File file) throws IOException {
		Workbook wb=null;    //HSSFWorkbook��ʵ����
		if(file.getName().endsWith(EXCEL_XLS)) { //endsWith()���Դ��ַ����Ƿ���ָ���ĺ�׺��β�� 
			wb=new HSSFWorkbook(in);
			//HSSFWorkbook֧���ԡ�xlsΪ��׺�Ķ����Ƹ�ʽ
		}else if(file.getName().endsWith(EXCEL_XLSX)) {
			wb=new XSSFWorkbook(in);
		}
		return wb;
	}
	
	//�ж��ļ��Ƿ���Excel�ļ�
	public static void checkExcelVaild(File file) throws Exception {
		if(!file.exists()) {
			throw new Exception("�ļ������ڣ�");
		}
		if(!(file.isFile()&&(file.getName().endsWith(EXCEL_XLS)||file.getName().endsWith(EXCEL_XLSX)))){//endsWith()���Դ��ַ����Ƿ���ָ���ĺ�׺��β�� 
			throw new Exception("�ļ�����Excel�ļ���");
		}
	}
	
	//���ص�Ԫ���ֵ
	private static Object getValue(Cell cell) {
		Object obj=null;
		switch(cell.getCellType()) {
		case BOOLEAN:
			obj=cell.getBooleanCellValue();break;
		case FORMULA:
			obj=cell.getCellFormula();break;
		case STRING:
			obj=cell.getStringCellValue();break;
		case NUMERIC:
			obj=cell.getDateCellValue();break;
			default:
				break;
		}
		return obj;
	}
	
	public static void disPlayRow(Workbook workbook) {
		try {
			//int sheetCount=workbook.getNumberOfSheets();//�õ�Sheet������
			Sheet sheet=workbook.getSheetAt(0);//������1��Sheet
			
			//������һ��Ŀ¼���б�ʶ��
			int count=0,mark=0;
			cellNum=0;rowNum=0;
			for(Row row:sheet) {
				if(count<1) {
					count++;
					continue;
				}
				if(row.getCell(0)==null) {
					return;
				}
				
				//int columnTotalNum=row.getPhysicalNumberOfCells();//��ȡ������
				//System.out.println("��������"+columnTotalNum);
				
				cellNum=row.getLastCellNum();  //�õ�����
				
				//��ɨ�����
				int end=row.getLastCellNum();
				for(int i=0;i<end;i++) {
					Cell cell=row.getCell(i);
					if(cell==null) {
						//System.out.print("null"+"\t");
						excelCell[mark++]=null;
						continue;
					}
				
				    Object obj=getValue(cell);
				    //System.out.print(obj+"\t");
				    excelCell[mark++]=obj+"";
				}
				//System.out.println();
				rowNum++;                       //����������Ŀ¼
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		//SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");//һ���������,ʹ�ø���ģʽ SimpleDateFormat��ʹ��Ĭ�ϵ� FORMAT���Ի�����Ĭ�����ڸ�ʽ���š� 
		try {	
			File excelFile=new File("C:\\Users\\17905\\Desktop\\j2ee\\Software17_Student_JavaEE.xlsx");//�����ļ�����
			FileInputStream in=new FileInputStream(excelFile);//�ļ���
			checkExcelVaild(excelFile);//�ж��ļ��Ƿ���Excel�ļ�
			Workbook workbook=getWorkbook(in,excelFile);
			disPlayRow(workbook);	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
