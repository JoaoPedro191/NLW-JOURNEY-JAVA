CREATE TABLE participants(
   id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   email VARCHAR(100) NOT NULL,
   is_confirmed BOOLEAN NOT NULL,
   created_at TIMESTAMP NOT NULL
   trip_id UUID,
   FOREIGN KEY (trip_id) REFERENCES trips(id)
);


