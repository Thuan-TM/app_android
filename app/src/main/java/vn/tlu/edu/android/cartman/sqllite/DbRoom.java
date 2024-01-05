package vn.tlu.edu.android.cartman.sqllite;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import vn.tlu.edu.android.cartman.R;
import vn.tlu.edu.android.cartman.product.model.Product;
import vn.tlu.edu.android.cartman.product.model.ProductCRUD;

@Database(entities = {UserTable.class}, version = 2, exportSchema = false)
public abstract class DbRoom extends RoomDatabase {

    public abstract UserCRUD User_crud();

//    public abstract ProductCRUD Product_crud();
    private static volatile DbRoom INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static DbRoom getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DbRoom.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DbRoom.class, "62pm2")
//                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static DbRoom.Callback sRoomDatabaseCallback = new DbRoom.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                UserCRUD dao = INSTANCE.User_crud();
                dao.deleteAll();

                // tạo user đầu tiên
                UserTable user = new UserTable("dorashang","xxx","Dora Shang",1705165200);
                dao.insert(user);

//
//                ProductCRUD dao_p = INSTANCE.Product_crud();
//                dao_p.deleteAll();
//
//                float price =  Float.parseFloat(String.format("%.2f",new Random().nextFloat() * 1000));
//                Product product = new Product("Hoa Cúc","Hoa Cúc rat dep",price, Uri.parse(String.valueOf(R.drawable.hoa_cuc)));
//                dao_p.insert(product);
//
//                float price1 =  Float.parseFloat(String.format("%.2f",new Random().nextFloat() * 1000));
//                Product product2 = new Product("Hoa Lan","Hoa Lan rat dep",price1,Uri.parse(String.valueOf(R.drawable.hoa_lan)));
//                dao_p.insert(product2);
//
//                float price3 =  Float.parseFloat(String.format("%.2f",new Random().nextFloat() * 1000));
//                Product product3 = new Product("Sống đời","Sống đời rat dep",price3,Uri.parse(String.valueOf(R.drawable.song_doi)));
//                dao_p.insert(product3);
//
//                float price4 =  Float.parseFloat(String.format("%.2f",new Random().nextFloat() * 1000));
//                Product product4 = new Product("Hoa Trạng nguyên","Hoa Trạng nguyên rat dep",price4,Uri.parse(String.valueOf(R.drawable.hoa_trang_nguyen)));
//                dao_p.insert(product4);
//
//                float price5 =  Float.parseFloat(String.format("%.2f",new Random().nextFloat() * 1000));
//                Product product5 = new Product("Hoa Cát tường","Hoa Cát tường rat dep",price5,Uri.parse(String.valueOf(R.drawable.hoa_cat_tuong)));
//                dao_p.insert(product5);
//
//
//                float price6 =  Float.parseFloat(String.format("%.2f",new Random().nextFloat() * 1000));
//                Product product6 = new Product("Hoa Đồng tiền","Hoa Đồng tiền rat dep",price6,Uri.parse(String.valueOf(R.drawable.hoa_dong_tien)));
//                dao_p.insert(product6);
//
//                float price7 =  Float.parseFloat(String.format("%.2f",new Random().nextFloat() * 1000));
//                Product product7 = new Product("Hoa Hồng","Hoa Hồng rat dep",price7,Uri.parse(String.valueOf(R.drawable.hoa_cuc)));
//                dao_p.insert(product7);
//
//                float price8 =  Float.parseFloat(String.format("%.2f",new Random().nextFloat() * 1000));
//                Product product8 = new Product("Hoa Vạn thọ","Hoa Vạn thọ rat dep",price8,Uri.parse(String.valueOf(R.drawable.hoa_van_tho)));
//                dao_p.insert(product8);
//
//                float price9 =  Float.parseFloat(String.format("%.2f",new Random().nextFloat() * 1000));
//                Product product9 = new Product("Hoa Hướng dương","Hoa Hướng dương rat dep",price9,Uri.parse(String.valueOf(R.drawable.hoa_cuc)));
//                dao_p.insert(product9);
//
//                float price10 =  Float.parseFloat(String.format("%.2f",new Random().nextFloat() * 1000));
//                Product product10 = new Product("Nụ tầm xuân","Nụ tầm xuân rat dep",price10,Uri.parse(String.valueOf(R.drawable.nu_tam_xuan)));
//                dao_p.insert(product10);
//
//                float price11 =  Float.parseFloat(String.format("%.2f",new Random().nextFloat() * 1000));
//                Product product11 = new Product("Hoa Đào","Hoa Đào rat dep",price11,Uri.parse(String.valueOf(R.drawable.hoa_dao)));
//                dao_p.insert(product11);
//
//                float price12 =  Float.parseFloat(String.format("%.2f",new Random().nextFloat() * 1000));
//                Product product12 = new Product("Hoa Mai vàng","Hoa Mai vàng rat dep",price12,Uri.parse(String.valueOf(R.drawable.hoa_cuc)));
//                dao_p.insert(product12);
//
//                float price13 =  Float.parseFloat(String.format("%.2f",new Random().nextFloat() * 1000));
//                Product product13 = new Product("Hoa hải đường","Hoa hải đường rat dep",price13,Uri.parse(String.valueOf(R.drawable.hoa_hai_duong)));
//                dao_p.insert(product13);
//
//                float price14 =  Float.parseFloat(String.format("%.2f",new Random().nextFloat() * 1000));
//                Product product14 = new Product("Hoa lay ơn","Hoa lay ơn rat dep",price14,Uri.parse(String.valueOf(R.drawable.hoa_lay_on)));
//                dao_p.insert(product14);
//
//                float price15 =  Float.parseFloat(String.format("%.2f",new Random().nextFloat() * 1000));
//                Product product15 = new Product("Hoa bách hợp","Hoa bách hợp rat dep",price15,Uri.parse(String.valueOf(R.drawable.hoa_cuc)));
//                dao_p.insert(product15);

            });
        }
    };

}
