package com.hyh.www.ExcelDemo;

import com.hyh.www.ExcelDemo.JDBCOperation;

//����Ϣ�м���н�����Ϣ,�����浽���ݿ���

public class ReceiveDemo {
	public static void main(String[] args) {
		ConsumerDemo cd=new ConsumerDemo();
		cd.getMessage();
		int num=cd.getCount()/8,mark=0;
		while((num--)>0) {
		    mark=JDBCOperation.insert(cd.getExcelCell(),mark);	
		}
		//JDBCOperation.getAll();
	}
}
