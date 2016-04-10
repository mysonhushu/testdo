package com.iamaction.tools.testdo.inter.util;

import com.iamaction.tools.testdo.inter.bean.LogType;
import com.iamaction.tools.testdo.inter.bean.TestInfo;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author huuuuxin
 */
public class XMLUtil {
    public static Document readXML(String pathName){
        Document doc = null;
        try {
            File fXmlFile = new File(pathName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        } catch (SAXException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        } catch (IOException ex) {
            Logger.getLogger(XMLUtil.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
        return doc;
    }
    
    public static List<TestInfo> readTestInfo(String pathName){
         List<TestInfo> tiList = new  ArrayList<TestInfo>();
        Document doc = readXML(pathName);
	NodeList nList = doc.getElementsByTagName("RUN_ENV");
	for (int temp = 0; temp < nList.getLength(); temp++) {
                TestInfo ti = new TestInfo();
		Node nNode = nList.item(temp);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
                        ti.HOST_IP = eElement.getAttribute("HOST_IP");
                        ti.OS_TYPE = eElement.getAttribute("OS_TYPE");
                        String aStr = eElement.getAttribute("INTERACTIVE").toLowerCase();
                        ti.INTERACTIVE = StringToBoolean(aStr,"error value "
                                + "at testdo-config.xml.the attribute value"
                                + " of INTERACTIVE must be yes or no!");
                        String bStr = eElement.getAttribute("LOG_TYPE").toLowerCase();
                       
                        if("tg".equals(bStr)){
                            ti.LOG_TYPE = LogType.TG_LOG;
                        }else if("ece".equals(bStr)){
                            ti.LOG_TYPE = LogType.ECE_LOG;
                        }else{
                            StringUtil.print("error value at testdo-config.xml. The "
                                    + "attribute value of LOG_TYPE must be TG or traffic!");
                            System.exit(-1);
                        }
                        String cStr = eElement.getAttribute("ACTIVE").toLowerCase();
                        System.out.println("cStr ="+cStr);
                        ti.ACTIVE = StringToBoolean(cStr,"error value at testdo-config.xml."
                                + "The attribute value of ACTIVE must be yes or no!");
                        
//			  System.out.println("HOST_IP : " + eElement.getAttribute("HOST_IP"));
//                        System.out.println("OS_TYPE : " + eElement.getAttribute("OS_TYPE"));
//                        System.out.println("INTERACTIVE : " + eElement.getAttribute("INTERACTIVE"));
//                        System.out.println("LOG_TYPE : " + eElement.getAttribute("LOG_TYPE"));
//                        System.out.println("ACTIVE : " + eElement.getAttribute("ACTIVE"));
                        
                        Node n1Node = eElement.getElementsByTagName("PROJECT").item(0);
                        if (n1Node.getNodeType() == Node.ELEMENT_NODE) {
                            Element e1Element = (Element) n1Node;
                            ti.PROJECT_NAME = e1Element.getAttribute("PROJECT_NAME");
                            ti.RUN_PATH = e1Element.getAttribute("RUN_PATH");
                            ti.CASE_PATH = e1Element.getAttribute("CASE_PATH");
                            ti.CASE_FILE_NAME = e1Element.getAttribute("CASE_FILE_NAME");
                            
//                            System.out.println("PROJECT_NAME : " + e1Element.getAttribute("PROJECT_NAME"));
//                            System.out.println("RUN_PATH : " + e1Element.getAttribute("RUN_PATH"));
//                            System.out.println("CASE_PATH : " + e1Element.getAttribute("CASE_PATH"));
//                            System.out.println("CASE_FILE_NAME : " + e1Element.getAttribute("CASE_FILE_NAME"));
                             
                            Node n2Node = e1Element.getElementsByTagName("COMMAND").item(0);
                            if(n2Node.getNodeType() == Node.ELEMENT_NODE) {
                                Element e2Element = (Element) n2Node;
                            
                                ti.COMMAND_VALUE = e2Element.getAttribute("COMMAND_VALUE");

                                String dStr = e2Element.getAttribute("FULL_CMD").toLowerCase();
                                StringUtil.print("FULL_CMD="+dStr);
                                ti.FULL_CMD = StringToBoolean(dStr,"error value at testdo-config.xml."
                                        + " The attribute value of FULL_CMD must be yes or no!");
                                ti.HTTP_PORT = e2Element.getAttribute("HTTP_PORT");
                                ti.APP_IP = e2Element.getAttribute("APP_IP");
                                ti.MD_IP = e2Element.getAttribute("MD_IP");
                                ti.TEST_CLASS = e2Element.getAttribute("TEST_CLASS");
                                tiList.add(ti);
//                                System.out.println("FULL_CMD : " + e2Element.getAttribute("FULL_CMD"));
//                                System.out.println("HTTP_PORT : " + e2Element.getAttribute("HTTP_PORT"));
//                                System.out.println("APP_IP : " + e2Element.getAttribute("APP_IP"));
//                                System.out.println("MD_IP : " + e2Element.getAttribute("MD_IP"));
//                                System.out.println("TEST_CLASS : " + e2Element.getAttribute("TEST_CLASS"));
                            }
                        }
		}
	}
        return tiList;
    }
    
    private static boolean StringToBoolean(String value, String errorMsg){
        boolean rt = false;
        if("yes".equals(value) || "true".equals(value)){
            rt = true;
        }else if("no".equals(value) || "false".equals(value)){
            rt = false;
        }else{
            StringUtil.print(errorMsg);
            System.exit(-1);
        }
        return rt;
    }
}
