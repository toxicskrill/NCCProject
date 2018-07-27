package com.example.Model;

import com.example.Entity.LogTimeSheet;

public class LogTimeSheetDTO {
	
	private boolean status ;
	private LogTimeSheet logTimeSheetInfo;
	
	public LogTimeSheetDTO(boolean status, LogTimeSheet logTimeSheetInfo) {
		super();
		this.status = status;
		this.logTimeSheetInfo = logTimeSheetInfo;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public LogTimeSheet getLogTimeSheetInfo() {
		return logTimeSheetInfo;
	}
	public void setLogTimeSheetInfo(LogTimeSheet logTimeSheetInfo) {
		this.logTimeSheetInfo = logTimeSheetInfo;
	}
	
	
	
}
