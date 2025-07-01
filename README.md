# Gallerist - Galeri Yönetim Sistemi 🚗

## 📌 Proje Hakkında

**Gallerist**, araç galerilerinin müşteri, araç ve hesap işlemlerini yönetebileceği, kurumsal düzeyde geliştirilen bir **galeri yönetim sistemidir**.  
Spring Boot altyapısı ile geliştirilen proje, sektörel standartlara uygun **JWT tabanlı güvenlik**, **katmanlı mimari** ve **özelleştirilmiş exception yönetimi** ile donatılmıştır.

---

## ✅ Özellikler

- 🔐 JWT (Json Web Token) ile kimlik doğrulama ve yetkilendirme  
- 📦 SOLID prensiplerine uygun servis katmanı mimarisi  
- 🔁 DTO ile güçlü servis/dış dünya ayrımı  
- 🛠️ Gelişmiş exception mimarisi (BaseException, ErrorMessage, MessageType)  
- ⚙️ Gerektiğinde BeanUtils yerine **custom mapper** kullanımı *(geliştirme aşamasında)*  

---

## 🛠️ Kullanılan Teknolojiler

- Spring Boot  
- Spring Security (JWT)  
- Spring Data JPA & Hibernate  
- Lombok  
- Jakarta Validation  
- RestTemplate  
- PostgreSQL  
- Maven  

---

## 🧱 Katmanlı Mimari

├── model # JPA entity sınıfları
├── dto # Veri transfer nesneleri
├── repository # Veritabanı işlemleri
├── service # İş mantığı (interface + impl)
├── controller # REST API uçları
├── exception # Özel hata yönetimi mimarisi
├── config / security # Güvenlik & yapılandırma
└── utils # Ek yardımcı sınıflar (örn: anlık tarih)


---

## ⚠️ Exception Mimarisi

Sistemde özelleştirilmiş bir hata yönetimi mimarisi kullanılmaktadır:

- `BaseException` → Tüm özel hataların kalıtıldığı temel sınıf  
- `ErrorMessage` → Hata mesajı ve hata kodu yapısı  
- `MessageType` → Enum tabanlı hata kodları  
```java
throw new BaseException(new ErrorMessage(MessageType.USER_NOT_FOUND, "muhammed"));



🔐 Güvenlik
JWT mimarisi sayesinde, kullanıcılar kimlik doğrulaması sonrası belirli uç noktalara erişebilir:

Endpoint	Açıklama
/authenticate	Giriş
/register	Yeni kullanıcı kaydı
/refreshToken	Token yenileme
/rest/api/...	Korunan işlemler

Tüm korumalı uç noktalar Authorization: Bearer <token> başlığı ile erişilebilir.



💱 Döviz API Entegrasyonu (TCMB)
Proje içerisinde /currency endpoint’i üzerinden Türkiye Cumhuriyet Merkez Bankası (TCMB) veri servisinden anlık USD döviz kuru çekilmektedir.

Örnek:
https://evds2.tcmb.gov.tr/service/evds/series=TP.DK.USD.A.YTL&startDate=01-07-2025&endDate=01-07-2025&type=json


🚀 Kurulum
bash👨‍💻 Geliştirici
👑 Muhammed Sosun
📧 sosunmuhammed@gmail.com
🐙 GitHub: github.com/sosunmuhammed
git clone https://github.com/sosunmuhammed/gallerist.git
cd gallerist
mvn clean install
mvn spring-boot:run

👨‍💻 Geliştirici
👑 Muhammed Sosun
📧 sosunmuhammed@gmail.com
🐙 GitHub: github.com/sosunmuhammed
