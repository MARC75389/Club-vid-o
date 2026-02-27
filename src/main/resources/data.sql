DROP database CassetteStore;
CREATE DATABASE CassetteStore;
USE CassetteStore;
INSERT INTO Admin(user_name,password,role) VALUES ("admin","$2a$10$XQY33lD2.lE./Xw.z7/C4.rJ1cR1Z/Z.zZ/Z.zZ.zZ.zZ.zZ.zZ.z","ADMIN"),("admin2","$2a$10$XQY33lD2.lE./Xw.z7/C4.rJ1cR1Z/Z.zZ/Z.zZ.zZ.zZ.zZ.zZ.z","ADMIN");


INSERT INTO Admin (user_name, password, role) VALUES ('dbuser', '$2y$10$.qkbukzzX21D.bqbI.B2R.tvWP90o/Y16QRWVLodw51BHft7ZWbc.', 'ADMIN'),
('dbadmin', '$2y$10$kp1V7UYDEWn17WSK16UcmOnFd1mPFVF6UkLrOOCGtf24HOYt8p1iC', 'ADMIN');
