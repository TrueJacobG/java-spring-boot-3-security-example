INSERT INTO users (email, password, salt, role, version, created_at, updated_at)
VALUES ('admin@mail.com', '0494c7710284f7069971d11e7f779c89404d1703bb37f17cc99bfbc70c4bae79',
        'DvhiYXhejMotvWeJr8kfiWRmEjNWgk8KMdwM5saUve1bA5fm2tW8jhul71Q5lFxMujFLG1uxdK7SIei73bOTwMpFh42INXkwVzpPTjKM5lGvCqr4DsrCFkytrOoaTZI',
        'ADMIN', '0', NOW(), NOW())
ON DUPLICATE KEY UPDATE email=email;
-- email: admin@mail.com
-- password: SecurePassword123