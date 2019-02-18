use platform;

INSERT INTO weekly_expenses_by_user (yyyymmdd, created_at, user_id,spent) VALUES
('20171028', toTimestamp(now()),d0f60aa8-54a9-4840-b70c-fe562b68842b,{'20171028': { account_type: 'CHECKING', amount: 100.0 }});

INSERT INTO weekly_income_by_user (yyyymmdd, created_at, user_id, earned) VALUES
('20171028', toTimestamp(now()),d0f60aa8-54a9-4840-b70c-fe562b68842b,{'20171028': { account_type: 'CHECKING', amount: 200.0 }});

INSERT INTO products_by_user (product_id, created_at, user_id, product_status) VALUES
(b0f60aa8-24a9-4840-b70c-fe562b68842b,toTimestamp(now()),d0f60aa8-54a9-4840-b70c-fe562b68842b,{'recommended': { status: 'recommended', created_at: toTimestamp(now())},
'matched': { status: 'matched', created_at: toTimestamp(now())}});

INSERT INTO accounts_by_productstatus (account_id, created_at, product_id, product_status) VALUES
(e0f60aa8-54a9-4840-b70c-fe562b68842b,toTimestamp(now()),b0f60aa8-24a9-4840-b70c-fe562b68842b,{'recommended': { status: 'recommended', created_at: toTimestamp(now())},
'matched': { status: 'matched', created_at: toTimestamp(now())}});

INSERT INTO financials_by_product (payments_received, fee_income_received, interest_income_received, created_at, product_id) VALUES
(1000.0, 300.0, 100.0, toTimestamp(now()), b0f60aa8-24a9-4840-b70c-fe562b68842b);

INSERT INTO correspondence_by_user (user_id, created_at, event, correspondence) VALUES
(d0f60aa8-54a9-4840-b70c-fe562b68842b,toTimestamp(now()), 'SAVINGS_PRODUCT_RECOMMENDED',{'SAVINGS_PRODUCT_RECOMMENDED': {event: 'recommendation', type: 'email', description: 'Sent product recommendation to user Barbara', created_at: now()}});

INSERT INTO platform_event (user_id, event_timestamp, event, message) VALUES
(d0f60aa8-54a9-4840-b70c-fe562b68842b, now(), 'USER_ACCOUNT_IS_INACTIVE','inactivity');

INSERT INTO platform_event (user_id, event_timestamp, event, message) VALUES
(d0f60aa8-54a9-4840-b70c-fe562b68842b, now(), 'BANKER_DASHBOARD_LOGIN','login occurred');
