CREATE TABLE employees (
  employee_id    NUMBER(6,0)  NOT NULL,
  first_name     VARCHAR2(20) NULL,
  last_name      VARCHAR2(25) NOT NULL,
  email          VARCHAR2(25) NOT NULL,
  phone_number   VARCHAR2(20) NULL,
  hire_date      DATE         NOT NULL,
  job_id         VARCHAR2(10) NOT NULL,
  salary         NUMBER(8,2)  NULL,
  commission_pct NUMBER(2,2)  NULL,
  manager_id     NUMBER(6,0)  NULL,
  department_id  NUMBER(4,0)  NULL
);
