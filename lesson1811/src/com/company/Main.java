package com.company;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
private static ArrayList<Product>products=new ArrayList<>();
private static ArrayList<Seller> sellers =new ArrayList<>();
private static ArrayList<Sells> sells=new ArrayList<>();
private static ArrayList<Warehouse> warehouses=new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory=SAXParserFactory.newInstance();
        SAXParser parser=factory.newSAXParser();

        XMLHandlerProduct handler=new XMLHandlerProduct();  //считывание данный с XML
        parser.parse(new File("resource/products"),handler);

        XMLHandlerSeller handlerSeller=new XMLHandlerSeller();
        parser.parse(new File("resource/sellers"),handlerSeller);

        XMLHandlerSells handlerSells=new XMLHandlerSells();
        parser.parse(new File("resource/Sells"),handlerSells);

        XMLHandlerWarehouse handlerWarehouse=new XMLHandlerWarehouse();
        parser.parse(new File("resource/warehouse"),handlerWarehouse);



/*  //проверка считывания
        for(Product product:products)
            System.out.println(product);
        for(Seller sellers:sellers)
            System.out.println(sellers);
        for(Sells sells:sells)
            System.out.println(sells);
        for(Warehouse warehouse:warehouses)
            System.out.println(warehouse);*/

        //записи в файл пока что не сделана
        HashMap answer1=allProduct();//задание 1 получение количества всех товаров по типам
        System.out.println(answer1);//id + колличество на складах


        double answer2= midlSellsBySellsColDay();//задание 2 среднее количество продаж
        System.out.println(answer2);//если считать только дни продаж (дни меж ними не считаются)


        double answer3 =midlSellsByDayDifference();//задание 2 среднее количество продаж
        System.out.println(answer3);//если считать днина основе разница первой и последней покупки


    }

    public static double midlSellsByDayDifference(){
        HashMap<LocalDate,Integer> sellsMap=getSellsMap();
        Set<LocalDate> dateList=  sellsMap.keySet();
        ArrayList<LocalDate> listofDate = new ArrayList<>(dateList);
        Collections.sort(listofDate);
        int listsize=listofDate.size();
        Long days = ChronoUnit.DAYS.between(listofDate.get(0), listofDate.get(listsize-1));//разница меж первой и последней продажей

        int allSells=0;
        ArrayList<Integer>allSellsList=new ArrayList<>(sellsMap.values());
        for (int i = 0; i <allSellsList.size() ; i++) {
            allSells+=allSellsList.get(i);
        }
        //System.out.println(allSells);
       // System.out.println(days);
        return  (double) allSells/days;
    }

    public static double midlSellsBySellsColDay(){
        HashMap<Date,Integer> sellsMap=getSellsMap();
        int allSells=0;
        ArrayList<Integer>allSellsList=new ArrayList<>(sellsMap.values());
        for (int i = 0; i <allSellsList.size() ; i++) {
            allSells+=allSellsList.get(i);
        }
        return (double) allSells/allSellsList.size();
    }


    public static HashMap getSellsMap(){
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
        return sellsMap;
    }

    public static HashMap<Integer, Integer> allProduct(){
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
    }

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
