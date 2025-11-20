-- Soft delete for reviews
ALTER TABLE reviews
ADD COLUMN is_deleted BOOLEAN DEFAULT FALSE,
ADD COLUMN deleted_at TIMESTAMP NULL;

-- Soft delete for comments
ALTER TABLE comments
ADD COLUMN is_deleted BOOLEAN DEFAULT FALSE,
ADD COLUMN deleted_at TIMESTAMP NULL;

-- Soft delete for review_likes
ALTER TABLE review_likes
ADD COLUMN is_deleted BOOLEAN DEFAULT FALSE,
ADD COLUMN deleted_at TIMESTAMP NULL;
