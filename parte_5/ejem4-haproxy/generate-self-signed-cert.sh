#!/bin/sh

# Generate a unique private key (KEY)
openssl genrsa -out mydomain.key 2048

# Generating a Certificate Signing Request (CSR)
openssl req -new -key mydomain.key -out mydomain.csr

# Creating a Self-Signed Certificate (CRT)
openssl x509 -req -days 365 -in mydomain.csr -signkey mydomain.key -out mydomain.crt

# Append KEY and CRT to mydomain.pem
cat mydomain.key mydomain.crt >> mydomain.pem
