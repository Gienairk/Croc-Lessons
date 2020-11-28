package com.company;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.w3c.dom.Document;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;


import static java.util.stream.Collectors.toMap;

public class Main {
//private static ArrayList<Product>products=new ArrayList<>();//листы и методы для считывания с файлов products и sellers для выпавших заданий не нужны
//private static ArrayList<Seller> sellers =new ArrayList<>();//поэтому места работы с ними закоментированны.
private static ArrayList<Sells> sells=new ArrayList<>();
private static ArrayList<Warehouse> warehouses=new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory=SAXParserFactory.newInstance();
        SAXParser parser=factory.newSAXParser();

        /*XMLHandlerProduct handler=new XMLHandlerProduct();  //считывание данный с XML
        parser.parse(new File("resource/products"),handler);*/

        /*XMLHandlerSeller handlerSeller=new XMLHandlerSeller();
        parser.parse(new File("resource/sellers"),handlerSeller);*/

        XMLHandlerSells handlerSells=new XMLHandlerSells();
        parser.parse(new File("resource/Sells"),handlerSells);

        XMLHandlerWarehouse handlerWarehouse=new XMLHandlerWarehouse();
        parser.parse(new File("resource/warehouse"),handlerWarehouse);





        HashMap answer1=allProduct2();//задание 1 получение количества всех товаров по типам
        System.out.println(answer1);//id + колличество на складах


        double answer2= midlSellsBySellsColDay();//задание 2 среднее количество продаж
        System.out.println(answer2);//если считать только дни продаж (дни меж ними не считаются)


        double answer3 =midlSellsByDayDifference();//задание 2 среднее количество продаж
        System.out.println(answer3);//если считать дни на основе разница первой и последней покупки


        Document doc=createDocument(answer1,answer2,answer3);
        WriteToFile(doc);
    }

    public static void  WriteToFile(Document doc){
        DOMImplementation impl = doc.getImplementation();
        var implLs = (DOMImplementationLS) impl.getFeature("LS", "3.0");
        LSSerializer ser = implLs.createLSSerializer();
        ser.getDomConfig().setParameter("format-pretty-print", true);
        ser.writeToURI(doc, "new.xml");

    }

    private static Document createDocument(HashMap allproduct,double ans21,double ans22) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        String ans1=allproduct.toString();
        String ans2= String.valueOf(ans21);
        String ans3= String.valueOf(ans22);

        Element rootElement = doc.createElement("root");
        Element childElement = doc.createElement("firstAnswer");
        Element childElement2 = doc.createElement("secondAnswers");
        Element firstans=doc.createElement("answer");
        Element secans=doc.createElement("answer");

        Text textNode1 = doc.createTextNode(ans1);
        Text textNode2 = doc.createTextNode(ans2);
        Text textNode3 = doc.createTextNode(ans3);

        doc.appendChild(rootElement);
        rootElement.appendChild(childElement);
        rootElement.appendChild(childElement2);
        childElement2.appendChild(firstans);
        childElement2.appendChild(secans);

        childElement.appendChild(textNode1);
        firstans.appendChild(textNode2);
        secans.appendChild(textNode3);

        return doc;
    }


    public static double midlSellsByDayDifference(){
        HashMap<LocalDate,Integer> sellsMap=getSellsMap();
        Set<LocalDate> dateList=  sellsMap.keySet();
        ArrayList<LocalDate> listofDate = new ArrayList<>(dateList);
        Collections.sort(listofDate);
        int listsize=listofDate.size();
        Long days = ChronoUnit.DAYS.between(listofDate.get(0), listofDate.get(listsize-1));//разница меж первой и последней продажей
        ArrayList<Integer>allSellsList=new ArrayList<>(sellsMap.values());

        Optional<Integer> sum=allSellsList.stream()
                .reduce((x,y)->x+y);
        //System.out.println(sum);

        return  (double) sum.get()/days;
    }


    public static double midlSellsBySellsColDay(){
        HashMap<Date,Integer> sellsMap=getSellsMap();
        ArrayList<Integer>allSellsList=new ArrayList<>(sellsMap.values());
        Optional<Integer> sum=allSellsList.stream()
                .reduce((x,y)->x+y);

        return (double) sum.get()/allSellsList.size();
    }


   /* public static HashMap getSellsMap(){ версия сбора дат без стрима
        HashMap<LocalDate,Integer> sellsMap=new HashMap<LocalDate,Integer>();
        LocalDate keyDate;
        int promsell;
        for(Sells sells:sells){
            keyDate=sells.getDate();
            if (sellsMap.containsKey(keyDate)){
                promsell=sellsMap.get(keyDate);
                sellsMap.replace(keyDate,promsell+sells.getNumberOfProducts());
            }else{
                sellsMap.put(keyDate,sells.getNumberOfProducts());
            }
        }
       // System.out.println(sellsMap);
        return sellsMap;
    }*/

    public static HashMap getSellsMap(){
        Map<LocalDate, Integer> col1=sells.stream().collect(
                toMap(Sells::getDate,Sells::getNumberOfProducts,(i1, i2) -> (i1+i2)));
        return (HashMap) col1;
    }

    /*public static HashMap<Integer, Integer> allProduct(){ версия метода для 1 задания без стрима
        HashMap<Integer,Integer> col=new HashMap<Integer,Integer>();
        int id;
        int pr;
        for(Warehouse warehouse:warehouses) {
            id=warehouse.getProductid();
            if (col.containsKey(id)){
               pr=col.get(id);
               col.replace(id,pr+warehouse.getNumber());
            }else{
                col.put(id,warehouse.getNumber());
            }
        }
        return col;
    }*/

    public static HashMap<Integer, Integer> allProduct2(){
        Map<Integer, Integer> col1=warehouses.stream().collect(
                toMap(Warehouse::getProductid,Warehouse::getNumber,(i1, i2) -> (i1+i2)));
        return (HashMap<Integer, Integer>) col1;
    }

/*
    private static class XMLHandlerProduct extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("product")) {
                int id = Integer.parseInt(attributes.getValue("id"));
                String name = attributes.getValue("name");
                products.add(new Product(id, name));
            }
        }
    }
*/
/*
    private static class XMLHandlerSeller extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("seller")) {
                int id = Integer.parseInt(attributes.getValue("id"));
                String surname = attributes.getValue("surname");
                String name = attributes.getValue("name");
                sellers.add(new Seller(id,surname, name));
            }
        }
    }
*/
    private static class XMLHandlerSells extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("sell")) {
                int id = Integer.parseInt(attributes.getValue("id"));
                int sellersid = Integer.parseInt(attributes.getValue("sellersid"));
                int productid = Integer.parseInt(attributes.getValue("productid"));
                int numberOfProducts = Integer.parseInt(attributes.getValue("numberOfProducts"));
                String datestring=attributes.getValue("date");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
                LocalDate date = LocalDate.parse(datestring, formatter);
               // date = LocalDate.parse(datestring);
                sells.add(new Sells(id,sellersid,productid,numberOfProducts,date));
            }
        }
    }

    private static class XMLHandlerWarehouse extends DefaultHandler {
        int id=0;
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

           if (qName.equals("seller")){
               id=Integer.parseInt(attributes.getValue("id"));
           }

           if (qName.equals("product")){
               int productid=Integer.parseInt(attributes.getValue("productid"));
               int cost=Integer.parseInt(attributes.getValue("cost"));
               int number=Integer.parseInt(attributes.getValue("number"));
               warehouses.add(new Warehouse(id,productid,cost,number));
           }
        }
    }
}
