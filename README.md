### ERD
#### USER
|column| description |example|
|--|------------|--|
|id| 고객 고유 식별자  |1|
|email| 고객 이메일     |test@example.com|
|name| 고객 이름      |kim|
|create_at| 생성일자       |2024-11-29 12:42:00|
|updated_at| 수정일자       |2024-11-29 14:42:00|

#### CURRENCY
| column        | description | example             |
|---------------|-------------|---------------------|
| id            | 통화 고유 식별자   | 1                   |
| exchange_rate | 환율          | 1430.00             |
| currency_name | 통화 이름       | USD                 |
| symbol        | 표시          | $                   |
| create_at     | 생성일자        | 2024-11-29 12:42:00 |
| updated_at    | 수정일자        | 2024-11-29 14:42:00 |

### EXCHANGEREQUEST
| column          | description | example             |
|-----------------|-------------|---------------------|
| id              | 환전 요청 고유 식별자 | 1                   |
| user_id         | 고객 고유 식별자   | 1                   |
| currency_id     | 환전 대상 통화 식별자 | 1                   |
| before_exchange | 환전 전 금액     | 10000               |
| after_exchange  | 환전 후 금액     | 6.99                |
| create_at       | 생성일자        | 2024-11-29 12:42:00 |
| updated_at      | 수정일자        | 2024-11-29 14:42:00 |

### API 명세
#### USER

|기능|HTTP|URL|Request|Response|
|--|--|--|--|--|
|고객 생성|POST|/users|{name:String,email:String}|{id:INT,name:String,email:String,createdAt:LocalDateTime,updatedAt:LocalDateTime}|
|고객 리스트 조회|GET|/users||{id:INT,name:String,email:String,createdAt:LocalDateTime,updatedAt:LocalDateTime}|
|고객 선택 조회|GET|/users/{id}||{id:INT,name:String,email:String,createdAt:LocalDateTime,updatedAt:LocalDateTime}|
|고객 삭제|DELETE|/users/{id}||{message:정상적으로 삭제되었습니다.}|

#### CURRENCY
|기능|HTTP|URL|Request|Response|
|--|--|--|--|--|
|통화 생성|POST|/currencies|{name:String,exchangeRate:BigDecimal,symbol:String}|{id:INT,name:String,exchangeRate:BigDecimal,symbol:String,createdAt:LocalDateTime,updatedAt:LocalDateTime}|
|통화 리스트 조회|GET|/currencies||{id:INT,name:String,exchangeRate:BigDecimal,symbol:String,createdAt:LocalDateTime,updatedAt:LocalDateTime}|
|통화 선택 조회|GET|/currencies/{id}||{id:INT,name:String,exchangeRate:BigDecimal,symbol:String,createdAt:LocalDateTime,updatedAt:LocalDateTime}|

#### EXCHANGEREQUEST
|기능|HTTP|URL|Request|Response|
|--|--|--|--|--|
|요청 생성|POST|/exchange|{userid:INT,currencyid:INT,beforeExchange:INT,status:String}|{id:INT,userid:INT,currencyid:INT,beforeExchange:INT,status:String,createdAt:LocalDateTime,updatedAt:LocalDateTime}|
|요청 취소 업데이트|PUT|/exchange/{id}/?status=cancelled||{id:INT,userid:INT,currencyid:INT,beforeExchange:INT,status:String,createdAt:LocalDateTime,updatedAt:LocalDateTime}|
|요청 선택 조회|GET|/exchange/user/{id}||{id:INT,userid:INT,currencyid:INT,beforeExchange:INT,status:String,createdAt:LocalDateTime,updatedAt:LocalDateTime}|
