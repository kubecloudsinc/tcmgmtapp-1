package com.cisco.cstg.autotools.semantic.test;


public class TestAttribute {
	
	private String testClassName;

	private String testMethodName;

	public String getTestClassName() {
		return testClassName;
	}

	public void setTestClassName(String testClassName) {
		this.testClassName = testClassName;
	}

	public String getTestMethodName() {
		return testMethodName;
	}

	public void setTestMethodName(String testMethodName) {
		this.testMethodName = testMethodName;
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) {
	            return true;
	        } else if (this.getTestClassName() == null) {
	            return false;
	        } else if (o instanceof TestAttribute) {
	            TestAttribute that = (TestAttribute) o;
	            return 
	            		(this.getTestClassName().compareToIgnoreCase(that.getTestClassName())==0)
	            		&&
	            		(this.getTestMethodName().compareToIgnoreCase(that.getTestMethodName())==0);
	        } else {
	            return false;
	        }
	    }

	    @Override
	    public int hashCode() {
	        return this.getTestClassName() == null ? System.identityHashCode(this) : 
	        	(17 * (this.getTestClassName().hashCode()) + 17 *(this.getTestMethodName().hashCode()));
	    }
}