# JsonSoapIntegration

## Суулгах заавар

### Шаардлага

- Java 21+
- Spring Boot 4.0.x
- MongoDB Atlas account
- Eclipse IDE
- Maven 3.8+

### 1. MongoDB Atlas тохируулах

1. [mongodb.com/atlas](https://mongodb.com/atlas) дээр бүртгэл үүсгэх
2. Үнэгүй M0 cluster үүсгэх
3. Connection string авах:

### 2. Environment Variables тохируулах

### 3. SOAP Service ажиллуулах

1. `user-soap-service` төслийг сонгох
2. `Run As` → `Spring Boot App` дарах
3. Port 8080-д сервис ажиллана

### 4. JSON Service ажиллуулах

1. `user-json-service` төслийг сонгох
2. `Run As` → `Spring Boot App` дарах
3. Port 8081-д сервис ажиллана

### Чухал анхаарал

**SOAP service заавал эхлээд ажиллаж байх ёстой**

---

## Үйлчилгээнд суурилсан архитектур

- **SOAP Service**: WSDL-д суурилсан веб үйлчилгээ (Port 8080)
- **JSON Service**: RESTful API (Port 8081)
- **Database**: MongoDB Atlas (Cloud)
