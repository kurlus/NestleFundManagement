
package webservices.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.1.4
 * Wed Aug 31 12:01:06 PKT 2016
 * Generated source version: 3.1.4
 */

@XmlRootElement(name = "loadDefinitionDataResponse", namespace = "http://webservices/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loadDefinitionDataResponse", namespace = "http://webservices/")

public class LoadDefinitionDataResponse {

    @XmlElement(name = "return")
    private app.DefResultSet _return;

    public app.DefResultSet getReturn() {
        return this._return;
    }

    public void setReturn(app.DefResultSet new_return)  {
        this._return = new_return;
    }

}
