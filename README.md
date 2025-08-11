# Kalori Tounsi Plus (MVP)

- واجهة بالدارجة التونسية
- حساب كالوري + بروفيل
- اقتراحات وجبات حسب: **صيام 16/8 (وجبتين)** أو **عادي (3 وجبات)**
- متابع الماء + تذكير بسيط
- قاعدة بيانات فيها أكلات تونسية ومكونات خام (كالوري/100غ للنايّ وين لزم)

## تشغيل
1. افتح في Android Studio
2. Build → Build APKs → تلقى `app-debug.apk`

## GitHub Actions
موجود `.github/workflows/android.yml` يخرّج APK على كل Push.


## صور المنتجات
تمت إضافة أيقونات بسيطة لكل صنف (drawable XML). يمكنك لاحقًا استبدال أيقونة بصورة حقيقية بنفس الاسم داخل `app/src/main/res/drawable/`.

## تنبيهات ماء/رياضة حسب الهدف
- الماء: كل 2/3/4 ساعات (نقص/تثبيت/زيادة).
- الرياضة: 8:30 و18:30 (نقص)، 18:00 (تثبيت)، 19:00 (زيادة).
تُجدول تلقائيًا عند فتح التطبيق.

## بناء APK عبر GitHub Actions (Step‑by‑Step)
1) أنشئ Repo جديد في GitHub وارفع المشروع (branch: `main`).
2) افتح تبويب **Actions** وتأكّد أن Workflow `Android APK (Debug & optional Release)` ظاهر.
3) اعمل **Commit/Push** أو شغّله يدويًا بـ **Run workflow**.
4) بعد ما يكمل الـ run: افتح **Artifacts** ونزّل **KaloriTounsi-debug-apk** → يوجد ملف `app-debug.apk`.
5) انسخ الـ APK إلى هاتفك وثبّته (فعّل Install unknown apps لو لزم).

> Android 13+: اسمح للإشعارات من إعدادات التطبيق حتى تظهر التنبيهات.
