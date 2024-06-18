```mermaid
block-beta
Frontend
block
    Backend space PostgreSQL_DB
    Backend --> PostgreSQL_DB
    PostgreSQL_DB --> Backend
end
PostgreSQL_DB --> Persistant_Storage
```
