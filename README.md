Smart Energy Management System

Bu proje, bir veritabanı yönetim sistemi (DBMS) projesi olup, çeşitli cihazlar, enerji tüketimi ve kullanıcı yönetimi ile ilgili işlemleri gerçekleştirmek amacıyla Java dilinde geliştirilmiştir. Proje, kullanıcıların enerji tüketimlerini izlemeleri, cihaz yönetimini yapmaları ve faturalarla ilgili işlemleri gerçekleştirmeleri için bir arayüz sunmaktadır.

İçerik
  AFrame.java: Uygulamanın ana arayüz bileşeni.
  ObjectHelper.java: Veritabanı ile etkileşim sağlayan yardımcı sınıf.
  Dal Klasörleri: Farklı veri erişim katmanlarını içerir.
    billingDal.java: Faturalama işlemleri için veri erişimi.
    DeviceDal.java: Cihaz yönetimi için veri erişimi.
    energyDal.java: Enerji tüketimi verisi ile etkileşim.
    UserDal.java: Kullanıcı yönetimi için veri erişimi.
  MainFrame.java: Ana uygulama çerçevesini sağlayan sınıf.
  Run.java: Uygulamanın başlatılmasını sağlayan ana sınıf.
  Model Sınıfları:
    Billing.java: Faturalama model sınıfı.
    Device.java: Cihaz yönetimi model sınıfı.
    EnergyConsumption.java: Enerji tüketimi model sınıfı.
    Users.java: Kullanıcı model sınıfı.
    Interfaces: Veri erişim katmanı için arayüzler (örneğin, IDataAccesLayer.java).
Teknolojiler
Java 8+: Uygulama geliştirme için kullanılan temel dil.
MySQL Connector/J 8.4.0: Veritabanı ile iletişim kurmak için kullanılan JDBC bağlantı kütüphanesi.
Swing: Java ile masaüstü uygulaması geliştirme için kullanılan GUI kütüphanesi.
JDBC: Veritabanı işlemleri için kullanılan Java veritabanı bağlantısı.
