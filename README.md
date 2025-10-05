# Embulk Encoder Encryption

An **Embulk encoder plugin** that provides **data encryption** before writing output files.  
This plugin helps secure sensitive data in ETL pipelines by encrypting records using algorithms like **AES**, with customizable key management and encoding options.

### ✨ Features
- 🔐 AES encryption for text and binary data  
- ⚙️ Configurable encryption key and IV handling  
- 🧩 Seamless integration with existing Embulk output plugins  
- 💾 Supports both streaming and batch encoding modes  

### 🧰 Example Usage
```yaml
out:
  type: file
  path_prefix: encrypted/output_
  encoder:
    type: encryption
    algorithm: AES/CBC/PKCS5Padding
    key: ${ENCRYPTION_KEY}
    iv: ${ENCRYPTION_IV}
```
