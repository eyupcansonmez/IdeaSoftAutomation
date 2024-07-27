## Proje Hakkında
Bu proje, `https://testcase.myideasoft.com/` sitesinde ürün arama, ürün detayına giriş, ürünü sepete ekleme ve sepet içeriğini kontrol etme işlemlerini otomatikleştiren bir test otomasyonudur.
## Test Adımları

1. **Ana Sayfa Ziyareti:**
   - `https://testcase.myideasoft.com/` sitesi ziyaret edilir.

2. **Ürün Arama:**
   - Ana sayfada bulunan arama kısmına "ürün" yazılarak arama yapılır.

3. **Ürün Detayına Giriş:**
   - Arama sonuçlarında listelenen ürünün detayına girilir.

4. **Ürünü Sepete Ekleme:**
   - İlgili üründen 5 adet seçilerek sepete eklenir.
   - "SEPETİNİZE EKLENMİŞTİR" yazısının görünmesi kontrol edilir.

5. **Sepet Sayfasına Gidiş:**
   - `/sepet` sayfasına gidilir.

6. **Sepet İçeriğini Kontrol:**
   - Sepet içeriğinde ilgili üründen 5 adet olduğu kontrol edilir.
