package com.kalori.tounsi.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kalori.tounsi.data.*
import com.kalori.tounsi.data.dao.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.kalori.tounsi.R

@Database(
    entities = [Food::class, MealEntry::class, UserProfile::class],
    version = 2
)
abstract class AppDB : RoomDatabase() {
    abstract fun foodDao(): FoodDao
    abstract fun mealDao(): MealDao
    abstract fun profileDao(): ProfileDao

    companion object {
        @Volatile private var INSTANCE: AppDB? = null

        fun get(context: Context): AppDB =
            INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(context, AppDB::class.java, "kalori.db")
                    .fallbackToDestructiveMigration()
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                get(context).foodDao().insertAll(seedFoods())
                            }
                        }
                    })
                    .build()
                INSTANCE = db
                db
            }

        private fun seedFoods(): List<Food> = listOf(
            Food(name="كسكسي بالعلوش", servingDesc="صحن متوسط (350غ
            ,Food(name="لوز", servingDesc="100غ", calories=579, imageRes = R.drawable.ic_almonds)
            ,Food(name="جوز", servingDesc="100غ", calories=654, imageRes = R.drawable.ic_walnut)
            ,Food(name="فستق", servingDesc="100غ", calories=560, imageRes = R.drawable.ic_pistachio)
            ,Food(name="كاجو", servingDesc="100غ", calories=553, imageRes = R.drawable.ic_cashew)
            ,Food(name="بندق", servingDesc="100غ", calories=628, imageRes = R.drawable.ic_hazelnut)
            ,Food(name="فول سوداني", servingDesc="100غ", calories=567, imageRes = R.drawable.ic_peanut)
            ,Food(name="بذور دوار الشمس", servingDesc="100غ", calories=584, imageRes = R.drawable.ic_sunflower_seed)
            ,Food(name="بذور القرع", servingDesc="100غ", calories=559, imageRes = R.drawable.ic_pumpkin_seed)
            ,Food(name="جوز البرازيل", servingDesc="100غ", calories=659, imageRes = R.drawable.ic_brazil_nut)
            ,Food(name="زبيب ذهبي", servingDesc="100غ", calories=302, imageRes = R.drawable.ic_raisin)
            ,Food(name="توت مجفف", servingDesc="100غ", calories=325, imageRes = R.drawable.ic_cranberry)
            ,Food(name="أناناس مجفف", servingDesc="100غ", calories=350, imageRes = R.drawable.ic_pineapple_dry)
            ,Food(name="مانجا مجففة", servingDesc="100غ", calories=319, imageRes = R.drawable.ic_mango_dry)
            ,Food(name="جوز الهند مجفف", servingDesc="100غ", calories=660, imageRes = R.drawable.ic_coconut_dry)
            ,Food(name="تفاح", servingDesc="100غ", calories=52, imageRes = R.drawable.ic_apple)
            ,Food(name="موز", servingDesc="100غ", calories=89, imageRes = R.drawable.ic_banana)
            ,Food(name="برتقال", servingDesc="100غ", calories=47, imageRes = R.drawable.ic_orange)
            ,Food(name="فراولة", servingDesc="100غ", calories=32, imageRes = R.drawable.ic_strawberry)
            ,Food(name="عنب", servingDesc="100غ", calories=69, imageRes = R.drawable.ic_grapes)
            ,Food(name="كيوي", servingDesc="100غ", calories=61, imageRes = R.drawable.ic_kiwi)
            ,Food(name="خوخ", servingDesc="100غ", calories=39, imageRes = R.drawable.ic_peach)
            ,Food(name="مشمش", servingDesc="100غ", calories=48, imageRes = R.drawable.ic_apricot)
            ,Food(name="كمثرى", servingDesc="100غ", calories=57, imageRes = R.drawable.ic_pear)
            ,Food(name="دلاع (بطيخ)", servingDesc="100غ", calories=30, imageRes = R.drawable.ic_watermelon)
            ,Food(name="شمام", servingDesc="100غ", calories=34, imageRes = R.drawable.ic_melon)
            ,Food(name="جوافة", servingDesc="100غ", calories=68, imageRes = R.drawable.ic_guava)
            ,Food(name="توت أزرق", servingDesc="100غ", calories=57, imageRes = R.drawable.ic_blueberry)
            ,Food(name="رمان", servingDesc="100غ", calories=83, imageRes = R.drawable.ic_pomegranate)
            ,Food(name="ليمون", servingDesc="100غ", calories=29, imageRes = R.drawable.ic_lemon))", calories=620),
            Food(name="شربة فريك", servingDesc="صحن (300غ)", calories=280),
            Food(name="لبلابي", servingDesc="صحن (400غ)", calories=450),
            Food(name="بريك", servingDesc="حبة (120غ)", calories=300),
            Food(name="مقروض", servingDesc="قطعة (60غ)", calories=240),
            Food(name="ملوخية لحم", servingDesc="صحن (300غ)", calories=500),
            Food(name="سلطة تونسية", servingDesc="صحن (250غ)", calories=180),
            Food(name="كسكروت تونسي", servingDesc="ساندويتش (220غ)", calories=520),
            Food(name="عصيدة زقوقو", servingDesc="كأس (180غ)", calories=390),
            Food(name="مسمن/ملاوي", servingDesc="واحدة (150غ)", calories=410),
            Food(name="كسكسي خضرة", servingDesc="صحن (350غ)", calories=520),
            Food(name="مقرونة صوص", servingDesc="صحن (300غ)", calories=560),

            Food(name="3dham masmoute", servingDesc="100غ", calories=520),
            Food(name="عسل", servingDesc="100غ", calories=304),
            Food(name="زيت زيتون", servingDesc="100غ", calories=884),
            Food(name="سلطة خضرا (من غير زيت)", servingDesc="صحن (250غ)", calories=80),
            Food(name="سلطة مشوية (من غير زيت)", servingDesc="صحن (250غ)", calories=95),
            Food(name="مقرونة BIO (نايّة)", servingDesc="100غ (ناي)", calories=371),
            Food(name="مقرونة كاملة (نايّة)", servingDesc="100غ (ناي)", calories=348),
            Food(name="كافي أو ليه (من غير سكر)", servingDesc="كاس (200مل)", calories=60),
            Food(name="أملات", servingDesc="100غ", calories=190),
            Food(name="بسيسة", servingDesc="100غ", calories=430),
            Food(name="تارالي", servingDesc="100غ", calories=457),
            Food(name="زبيب (مجفف)", servingDesc="100غ", calories=299),
            Food(name="تمر (مجفف)", servingDesc="100غ", calories=282),
            Food(name="قراصيا (مجفف)", servingDesc="100غ", calories=240),
            Food(name="تين مجفف", servingDesc="100غ", calories=249),
            Food(name="مشمش مجفف", servingDesc="100غ", calories=241),
            Food(name="ياورت 0%", servingDesc="علبة (125غ)", calories=45),
            Food(name="ريكوتا", servingDesc="100غ", calories=174),
            Food(name="فرماج طري للدهن", servingDesc="100غ", calories=350),
            Food(name="رايب", servingDesc="كاس (200مل)", calories=80),
            Food(name="لبن", servingDesc="كاس (200مل)", calories=95),
            Food(name="سمك (نايّ)", servingDesc="100غ (قبل الشوي/الطبيخ)", calories=120),
            Food(name="لحم علوش (نايّ)", servingDesc="100غ (قبل الطبيخ)", calories=240),
            Food(name="إسكالوب دجاج (ناي)", servingDesc="100غ (قبل الطبيخ)", calories=120),
            Food(name="كبدة علوش (نايّة)", servingDesc="100غ (قبل الطبيخ)", calories=139),
            Food(name="ملاوي", servingDesc="واحدة (150غ)", calories=410),
            Food(name="بيتزا نابوليتان بالتنّ", servingDesc="100غ", calories=266),
            Food(name="روز (نايّ)", servingDesc="100غ (قبل الطبيخ)", calories=360),
            Food(name="كسكسي (سميد نايّ)", servingDesc="100غ (قبل الطبيخ)", calories=376)
        )
    }
}
