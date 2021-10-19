package app.beans;

import java.io.Serializable;

public class Authority implements Serializable {

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	public Authority() {
		this.v=true;
		this.s=true;
		this.p=true;
		this.u=true;
		this.d=true;
	
		// TODO Auto-generated constructor stub
	}

	

	public boolean isV() {
		return v;
	}

	public void setV(boolean v) {
		this.v = v;
	}

	public boolean isS() {
		return s;
	}

	public void setS(boolean s) {
		this.s = s;
	}

	public boolean isP() {
		return p;
	}

	public void setP(boolean p) {
		this.p = p;
	}

	public boolean isU() {
		return u;
	}

	public void setU(boolean u) {
		this.u = u;
	}

	public boolean isD() {
		return d;
	}

	public void setD(boolean d) {
		this.d = d;
	}
	
	
	public Authority(String authorityName, boolean v, boolean s, boolean p,
			boolean u, boolean d) {
		super();
		this.authorityName = authorityName;
		this.v = v;
		this.s = s;
		this.p = p;
		this.u = u;
		this.d = d;
	}
	
	




	private String authorityName;
	private boolean v=true;
	private boolean s=true;
	private boolean p=true;
	private boolean u=true;
	private boolean d=true;
}
