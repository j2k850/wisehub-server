use platform;

INSERT INTO user_credentials (email, password,user_id) VALUES
('biyayi@wisehub.io', 'biyayi1234',d0f60aa8-54a9-4840-b70c-fe562b68842b);

INSERT INTO users (user_id, bvn, name, email, addresses, created_at, updated_at) VALUES
(d0f60aa8-54a9-4840-b70c-fe562b68842b, 38612218715, {first_name: 'Barbara', last_name: 'Iyayi'},
'biyayi@wisehub.io', {'main': { street: '1 Wisehub Way', city: 'Lagos', postal_code: '101241', region: 'Victoria Island', country: 'Nigeria', phones: {'16467521228'}}},
toTimestamp(now()), toTimestamp(now()));

INSERT INTO accounts (account_id, branch_id, user_id, account_number, account_name, account_type, account_status, created_at, updated_at) VALUES
(e0f60aa8-54a9-4840-b70c-fe562b68842b, f0f60aa8-54a9-4840-b70c-fe562b68842b, d0f60aa8-54a9-4840-b70c-fe562b68842b, 1004605806, 'BARBARA OSAZE IYAYI', 'CHECKING','ACTIVE',toTimestamp(now()), toTimestamp(now()));

INSERT INTO branches (branch_id, fin_inst_id, branch_name, branch_mgr, branch_mgr_email, addresses, created_at, updated_at) VALUES
(f0f60aa8-54a9-4840-b70c-fe562b68842b, a0f60aa8-74a9-4840-b70c-fe562b68842b, 'Head Office', 'Tola Smith','enquiry@zenithbank.com',{'main': { street: '224A Awolowo Road', city: 'Lagos', postal_code: '101241', region: 'Ikoyi', country: 'Nigeria', phones: {'12342927000'}}},toTimestamp(now()), toTimestamp(now()));

INSERT INTO transactions (tx_id, account_id, user_id, description, deposits, withdrawals, balance, date_posted, created_at, updated_at) VALUES
(b0f60aa8-54a9-4840-b70c-fe562b68842b, e0f60aa8-54a9-4840-b70c-fe562b68842b, d0f60aa8-54a9-4840-b70c-fe562b68842b, 'Airtime//09031904969//MTN', 0.00, 10000.00, 534180.35, '2017-10-27', toTimestamp(now()), toTimestamp(now()));

INSERT INTO financial_institutions (fin_inst_id, financial_institution, addresses, created_at, updated_at) VALUES
(a0f60aa8-74a9-4840-b70c-fe562b68842b, 'A Big Bank', {'main': { street: '224A Awolowo Road', city: 'Lagos', postal_code: '101241', region: 'Ikoyi', country: 'Nigeria', phones: {'12342927000'}}},toTimestamp(now()), toTimestamp(now()));

INSERT INTO products (product_id, fin_inst_id, product_name, rules, created_at, updated_at) VALUES
(b0f60aa8-24a9-4840-b70c-fe562b68842b,a0f60aa8-74a9-4840-b70c-fe562b68842b, 'Individual Savings Account', {'main': {rule_name: 'Bank Rule', rule_description: 'Need to have 1000 naira in account.'}},toTimestamp(now()), toTimestamp(now()));

INSERT INTO user_fin_insts (user_id, created_at, fin_inst_id) VALUES (d0f60aa8-54a9-4840-b70c-fe562b68842b, toTimestamp(now()), a0f60aa8-74a9-4840-b70c-fe562b68842b);


INSERT INTO dashboard_roles (dashboard_role_id, dashboard_name, created_at,updated_at) VALUES
(ee2a4443-c9ea-47da-a2a5-a5ca3c328823, 'ADMIN',toTimestamp(now()),toTimestamp(now()));

INSERT INTO dashboard_roles (dashboard_role_id, dashboard_name, created_at,updated_at) VALUES
(a8ba9267-0bf3-4b86-bb70-f1dcff6af701, 'USER',toTimestamp(now()),toTimestamp(now()));
