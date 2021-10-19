package app.beans;

import java.io.Serializable;

public class ProjectProps implements Serializable
{
	private static final long serialVersionUID = 6919915131025046516L;
	
	public String getJDBCDriverClassName() 
	{
		return JDBCDriverClassName;
	}
	
	public void setJDBCDriverClassName(String jDBCDriverClassName) 
	{
		JDBCDriverClassName = jDBCDriverClassName;
	}
	
	public String getJdbcURL() 
	{
		return jdbcURL;
	}
	
	public void setJdbcURL(String jdbcURL) 
	{
		this.jdbcURL = jdbcURL;
	}
	
	public String getJdbcUserName() 
	{
		return jdbcUserName;
	}
	
	public void setJdbcUserName(String jdbcUserName) 
	{
		this.jdbcUserName = jdbcUserName;
	}
	
	public String getJdbcPassword() 
	{
		return jdbcPassword;
	}
	
	public void setJdbcPassword(String jdbcPassword) 
	{
		this.jdbcPassword = jdbcPassword;
	}
	
	public String getLogInfo() 
	{
		return logInfo;
	}
	
	public void setLogInfo(String logInfo) 
	{
		this.logInfo = logInfo;
	}
	
	public String getLogDebug() 
	{
		return logDebug;
	}
	
	public void setLogDebug(String logDebug) 
	{
		this.logDebug = logDebug;
	}
	
	public String getLogFileName() 
	{
		return logFileName;
	}
	
	public void setLogFileName(String logFileName) 
	{
		this.logFileName = logFileName;
	}
	
	public String getLogFileLocation() 
	{
		return logFileLocation;
	}
	
	public void setLogFileLocation(String logFileLocation) 
	{
		this.logFileLocation = logFileLocation;
	}
	
	public String getLogSessionTimeOut() 
	{
		return logSessionTimeOut;
	}
	
	public void setLogSessionTimeOut(String logSessionTimeOut) 
	{
		this.logSessionTimeOut = logSessionTimeOut;
	}
	
	public String getSmsByTableProps() 
	{
		return smsByTableProps;
	}
	
	public void setSmsByTableProps(String smsByTableProps) 
	{
		this.smsByTableProps = smsByTableProps;
	}
	
	public String getSmsByURLProps() 
	{
		return smsByURLProps;
	}
	
	public void setSmsByURLProps(String smsByURLProps) 
	{
		this.smsByURLProps = smsByURLProps;
	}
	
	public String getIsSMSEnable() 
	{
		return isSMSEnable;
	}
	
	public void setIsSMSEnable(String isSMSEnable) 
	{
		this.isSMSEnable = isSMSEnable;
	}
	
	public String getIsEmailEnable() 
	{
		return isEmailEnable;
	}
	
	public void setIsEmailEnable(String isEmailEnable) 
	{
		this.isEmailEnable = isEmailEnable;
	}
	
	public String getMailSMTPHost() 
	{
		return mailSMTPHost;
	}
	
	public void setMailSMTPHost(String mailSMTPHost) 
	{
		this.mailSMTPHost = mailSMTPHost;
	}
	
	public String getMailSMTPAuth() 
	{
		return mailSMTPAuth;
	}
	
	public void setMailSMTPAuth(String mailSMTPAuth) 
	{
		this.mailSMTPAuth = mailSMTPAuth;
	}
	
	public String getMailSMTPUser() 
	{
		return mailSMTPUser;
	}
	
	public void setMailSMTPUser(String mailSMTPUser) 
	{
		this.mailSMTPUser = mailSMTPUser;
	}
	
	public String getMailSMTPPassword() 
	{
		return mailSMTPPassword;
	}
	
	public void setMailSMTPPassword(String mailSMTPPassword) 
	{
		this.mailSMTPPassword = mailSMTPPassword;
	}
	
	public String getMailSMTPDebug() 
	{
		return mailSMTPDebug;
	}
	
	public void setMailSMTPDebug(String mailSMTPDebug) 
	{
		this.mailSMTPDebug = mailSMTPDebug;
	}
	
	public String getMailSMTPSSLTrust() 
	{
		return mailSMTPSSLTrust;
	}
	
	public void setMailSMTPSSLTrust(String mailSMTPSSLTrust) 
	{
		this.mailSMTPSSLTrust = mailSMTPSSLTrust;
	}
	
	public String getMailSMTPPort() 
	{
		return mailSMTPPort;
	}
	
	public void setMailSMTPPort(String mailSMTPPort) 
	{
		this.mailSMTPPort = mailSMTPPort;
	}
	
	public String getMailSMTPstarttlsEnable() 
	{
		return mailSMTPstarttlsEnable;
	}
	
	public void setMailSMTPstarttlsEnable(String mailSMTPstarttlsEnable) 
	{
		this.mailSMTPstarttlsEnable = mailSMTPstarttlsEnable;
	}
	
	public String getMailSMTPFromEmail() 
	{
		return mailSMTPFromEmail;
	}
	
	public void setMailSMTPFromEmail(String mailSMTPFromEmail) 
	{
		this.mailSMTPFromEmail = mailSMTPFromEmail;
	}
	
	public String getMailSMTPToEmail() 
	{
		return mailSMTPToEmail;
	}
	
	public void setMailSMTPToEmail(String mailSMTPToEmail) 
	{
		this.mailSMTPToEmail = mailSMTPToEmail;
	}
	
	public String getEmailCC() 
	{
		return emailCC;
	}
	
	public void setEmailCC(String emailCC) 
	{
		this.emailCC = emailCC;
	}
	
	public String getEmailBcc() 
	{
		return emailBcc;
	}
	
	public void setEmailBcc(String emailBcc) 
	{
		this.emailBcc = emailBcc;
	}
	
	public String getURL() 
	{
		return URL;
	}
	
	public void setURL(String uRL) 
	{
		URL = uRL;
	}
	
	public String getUserName() 
	{
		return userName;
	}
	
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}
	
	public String getSecret() 
	{
		return secret;
	}
	
	public void setSecret(String secret) 
	{
		this.secret = secret;
	}
	
	public String getNotificationFolioNumber() 
	{
		return notificationFolioNumber;
	}
	
	public void setNotificationFolioNumber(String notificationFolioNumber) 
	{
		this.notificationFolioNumber = notificationFolioNumber;
	}
	
	public String getTransactionType() 
	{
		return transactionType;
	}
	
	public void setTransactionType(String transactionType) 
	{
		this.transactionType = transactionType;
	}
	
	public String getNotificationRecordingDate() 
	{
		return notificationRecordingDate;
	}
	
	public void setNotificationRecordingDate(String notificationRecordingDate) 
	{
		this.notificationRecordingDate = notificationRecordingDate;
	}
	
	public String getNotificationText() 
	{
		return notificationText;
	}
	
	public void setNotificationText(String notificationText) 
	{
		this.notificationText = notificationText;
	}
	
	public String getSendTo() 
	{
		return sendTo;
	}
	
	public void setSendTo(String sendTo) 
	{
		this.sendTo = sendTo;
	}
	
	public String getSendingStatus() 
	{
		return sendingStatus;
	}
	
	public void setSendingStatus(String sendingStatus) 
	{
		this.sendingStatus = sendingStatus;
	}
	
	public String getSmsTabledatabaseIsSame() 
	{
		return smsTabledatabaseIsSame;
	}
	
	public void setSmsTabledatabaseIsSame(String smsTabledatabaseIsSame) 
	{
		this.smsTabledatabaseIsSame = smsTabledatabaseIsSame;
	}
	
	public String getSmsTableName() 
	{
		return smsTableName;
	}
	
	public void setSmsTableName(String smsTableName) 
	{
		this.smsTableName = smsTableName;
	}
	
	public String getSmsTableKeyColumn() 
	{
		return smsTableKeyColumn;
	}
	
	public void setSmsTableKeyColumn(String smsTableKeyColumn) 
	{
		this.smsTableKeyColumn = smsTableKeyColumn;
	}
	
	public static long getSerialversionuid() 
	{
		return serialVersionUID;
	}
	
	public String getCgtCalcOnSystemDate() 
	{
		return cgtCalcOnSystemDate;
	}

	public void setCgtCalcOnSystemDate(String cgtCalcOnSystemDate) 
	{
		this.cgtCalcOnSystemDate = cgtCalcOnSystemDate;
	}
	
	public String getCgtCalcFirstRun() 
	{
		return cgtCalcFirstRun;
	}

	public void setCgtCalcFirstRun(String cgtCalcFirstRun) 
	{
		this.cgtCalcFirstRun = cgtCalcFirstRun;
	}
	
	public String getSocketLogFileName() 
	{
		return socketLogFileName;
	}

	public void setSocketLogFileName(String socketLogFileName) 
	{
		this.socketLogFileName = socketLogFileName;
	}

	public String getSocketLogFileLocation() 
	{
		return socketLogFileLocation;
	}

	public void setSocketLogFileLocation(String socketLogFileLocation) 
	{
		this.socketLogFileLocation = socketLogFileLocation;
	}

	public String getSocketServerIP() 
	{
		return socketServerIP;
	}

	public void setSocketServerIP(String socketServerIP) 
	{
		this.socketServerIP = socketServerIP;
	}

	public String getSocketServerPort() 
	{
		return socketServerPort;
	}

	public void setSocketServerPort(String socketServerPort) 
	{
		this.socketServerPort = socketServerPort;
	}
	
	public String getByteBufferSize() 
	{
		return byteBufferSize;
	}

	public void setByteBufferSize(String byteBufferSize) 
	{
		this.byteBufferSize = byteBufferSize;
	}
	
	public String getUpLoadFilePath() 
	{
		return upLoadFilePath;
	}

	public void setUpLoadFilePath(String upLoadFilePath) 
	{
		this.upLoadFilePath = upLoadFilePath;
	}
	
	public String getDfltExcelFileRecRead() 
	{
		return dfltExcelFileRecRead;
	}

	public void setDfltExcelFileRecRead(String dfltExcelFileRecRead) 
	{
		this.dfltExcelFileRecRead = dfltExcelFileRecRead;
	}


	private String URL;
	private String secret;
	private String sendTo;
	private String emailCC;
	private String jdbcURL;
	private String logInfo;
	private String logDebug;
	private String emailBcc;
	private String userName;
	private String isSMSEnable;
	private String logFileName;	
	private String jdbcUserName;
	private String smsTableName;
	private String mailSMTPHost;
	private String jdbcPassword;
	private String mailSMTPAuth;
	private String mailSMTPUser;
	private String mailSMTPPort;	
	private String smsByURLProps;
	private String isEmailEnable;
	private String mailSMTPDebug;
	private String sendingStatus;
	private String upLoadFilePath;
	private String byteBufferSize;
	private String socketServerIP;	
	private String cgtCalcFirstRun;
	private String transactionType;	
	private String mailSMTPToEmail;
	private String logFileLocation;
	private String smsByTableProps;
	private String socketServerPort;
	private String notificationText;
	private String mailSMTPSSLTrust;
	private String mailSMTPPassword;	
	private String mailSMTPFromEmail;
	private String socketLogFileName;
	private String logSessionTimeOut;	
	private String smsTableKeyColumn;	
	private String cgtCalcOnSystemDate;	
	private String JDBCDriverClassName;
	private String dfltExcelFileRecRead;
	private String socketLogFileLocation;
	private String smsTabledatabaseIsSame;
	private String mailSMTPstarttlsEnable;
	private String notificationFolioNumber;
	private String notificationRecordingDate;
}
