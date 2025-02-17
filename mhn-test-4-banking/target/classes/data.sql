-- Insert data into TB_CUSTOMER_BANKING
INSERT INTO TB_CUSTOMER_BANKING (CUSTOMER_NAME, PHONE, CUSTOMER_NUMBER, NATIONAL_CODE)
VALUES ('John Doe', '1234567890', 'CUST001', 'NAT001');

INSERT INTO TB_CUSTOMER_BANKING (CUSTOMER_NAME, PHONE, CUSTOMER_NUMBER, NATIONAL_CODE)
VALUES ('Jane Smith', '0987654321', 'CUST002', 'NAT002');

-- Insert data into TB_ACCOUNT_BANKING
INSERT INTO TB_ACCOUNT_BANKING (ACCOUNT_NUMBER, ACCOUNT_DATE, ACCOUNT_TYPE, ACCOUNT_AMOUNT, ACTIVE_STATUS, CUSTOMER_ID)
VALUES ('ACC001', TO_DATE('2024-08-01', 'YYYY-MM-DD'), 1, 5000.00, 1, 1);

INSERT INTO TB_ACCOUNT_BANKING (ACCOUNT_NUMBER, ACCOUNT_DATE, ACCOUNT_TYPE, ACCOUNT_AMOUNT, ACTIVE_STATUS, CUSTOMER_ID)
VALUES ('ACC002', TO_DATE('2024-08-01', 'YYYY-MM-DD'), 2, 10000.00, 1, 2);
