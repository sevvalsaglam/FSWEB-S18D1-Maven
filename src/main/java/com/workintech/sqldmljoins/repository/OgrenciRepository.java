package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {


    //Kitap alan öğrencilerin öğrenci bilgilerini listeleyin..
    String QUESTION_2 = "SELECT o.* FROM ogrenci AS o\n" +
            "INNER JOIN islem AS i\n" +
            "ON o.ogrno=i.ogrno";
    @Query(value = QUESTION_2, nativeQuery = true)
    List<Ogrenci> findStudentsWithBook();


    //Kitap almayan öğrencileri listeleyin.
    String QUESTION_3 = "SELECT o.* FROM ogrenci AS o\n" +
            "LEFT JOIN islem AS i\n" +
            "ON o.ogrno=i.ogrno\n" +
            "WHERE i.ogrno is null";
    @Query(value = QUESTION_3, nativeQuery = true)
    List<Ogrenci> findStudentsWithNoBook();

    //10A veya 10B sınıfındaki öğrencileri sınıf ve okuduğu kitap sayısını getirin.
    String QUESTION_4 = "SELECT o.sinif ,COUNT(i.kitapno) FROM ogrenci AS o\n" +
            "LEFT JOIN islem AS i\n" +
            "ON o.ogrno=i.ogrno\n" +
            "WHERE o.sinif IN('10A','10B')\n" +
            "GROUP BY o.sinif";
    @Query(value = QUESTION_4, nativeQuery = true)
    List<KitapCount> findClassesWithBookCount();

    //Öğrenci tablosundaki öğrenci sayısını gösterin
    String QUESTION_5 = "SELECT COUNT (*) FROM ogrenci";
    @Query(value = QUESTION_5, nativeQuery = true)
    Integer findStudentCount();

    //Öğrenci tablosunda kaç farklı isimde öğrenci olduğunu listeleyiniz.
    String QUESTION_6 = "SELECT COUNT(DISTINCT ogrenci.ad) FROM ogrenci";
    @Query(value = QUESTION_6, nativeQuery = true)
    Integer findUniqueStudentNameCount();

    //--İsme göre öğrenci sayılarının adedini bulunuz.
    //--Ali: 2, Mehmet: 3
    String QUESTION_7 = "SELECT ogrenci.ad,\n" +
            "COUNT(ogrenci.ad) FROM ogrenci\n" +
            "GROUP BY ogrenci.ad";
    @Query(value = QUESTION_7, nativeQuery = true)
    List<StudentNameCount> findStudentNameCount();


    String QUESTION_8 = "SELECT ogrenci.sinif,\n" +
            "COUNT(ogrenci.ad) FROM ogrenci\n" +
            "GROUP BY ogrenci.sinif";
    @Query(value = QUESTION_8, nativeQuery = true)
    List<StudentClassCount> findStudentClassCount();

    String QUESTION_9 = "SELECT ogrenci.ad, ogrenci.soyad,\n" +
            "COUNT(islem.kitapno)\n" +
            "FROM ogrenci\n" +
            "INNER JOIN islem ON ogrenci.ogrno=islem.ogrno\n" +
            "GROUP BY ogrenci.ad, ogrenci.soyad";
    @Query(value = QUESTION_9, nativeQuery = true)
    List<StudentNameSurnameCount> findStudentNameSurnameCount();
}
