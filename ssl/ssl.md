# Generate local SSL certificate


For bash/zsh

```
openssl req -x509 -out nodes2020.crt -keyout nodes2020.key \
  -newkey rsa:2048 -nodes -sha256 \
  -subj '/CN=nodes2020' -extensions EXT -config <( \
   printf '[dn]\nCN=nodes2020\n[req]\ndistinguished_name = dn\n[EXT]\nsubjectAltName=DNS:nodes2020\nkeyUsage=digitalSignature\nextendedKeyUsage=serverAuth')
```

For fish shell

```
openssl req -x509 -out nodes2020.crt -keyout nodes2020.key \
  -newkey rsa:2048 -nodes -sha256 \
  -subj '/CN=nodes2020' -extensions EXT -config ( \
   printf '[dn]\nCN=nodes2020\n[req]\ndistinguished_name = dn\n[EXT]\nsubjectAltName=DNS:nodes2020\nkeyUsage=digitalSignature\nextendedKeyUsage=serverAuth' | psub)
```