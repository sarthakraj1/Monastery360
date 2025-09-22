package com.monastery360.tourism.data

object MonasteryRepository {
    fun getMonasteries(): List<Monastery> {
        return listOf(
            Monastery(
                id = 1,
                name = "Rumtek Monastery",
                location = "Rumtek, East Sikkim, India",
                description = "The largest monastery in Sikkim and the seat-in-exile of the Karmapa of the Karma Kagyu lineage, Rumtek Monastery is renowned for its grand Tibetan architecture, spiritual significance, and collection of religious art objects. It is located 24 km from Gangtok and offers views of lush green mountains.",
                imageUrl = "https://hblimg.mmtcdn.com/content/hubble/img/gangtok/mmt/activities/m_activities-gangtok-rumtek-monastery_l_400_640.jpg",
                latitude = 27.3478,
                longitude = 88.6202
            ),
            Monastery(
                id = 2,
                name = "Pemayangtse Monastery",
                location = "Pelling, West Sikkim, India",
                description = "One of Sikkim's oldest and most prestigious Nyingma order monasteries, founded in the 17th century. It features intricate woodwork, statues, and the famous 'Sanghthokpalri' Heavenly Palace sculpture. Its scenic hilltop location near Pelling offers expansive Himalayan views.",
                imageUrl = "https://s7ap1.scene7.com/is/image/incredibleindia/spiritual-spots-in-pelling-popular?qlt=82&ts=1726655959297",
                latitude = 27.3012,
                longitude=88.2330
            ),
            Monastery(
                id = 3,
                name = "Tashiding Monastery",
                location = "Tashiding, West Sikkim, India",
                description = "Regarded as the holiest and spiritually central monastery in Sikkim, Tashiding was established in 1641 and is famous for its ancient legends, spiritual festivals, and dramatic hilltop setting between Rathong Chu and Rangeet River.",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSI2ecn6QaKQhJO1qgpheCYMZCsb2LusHjgTw&s",
                latitude = 27.3000,
                longitude = 88.6000
            ),
            Monastery(
                id = 4,
                name = "Kartok Monastery",
                location = "Yuksom, West Sikkim, India",
                description = "Kartok Monastery, a beautiful red-and-gold Buddhist shrine beside the serene Kartok Lake in Yuksom, dates back to the 17th century. It is noted for its tranquil atmosphere, vibrant Tibetan architecture, and views of the Kanchenjunga range.",
                imageUrl = "https://www.tourmyindia.com/states/sikkim/images/tashiding-monastery.jpg",
                latitude =  27.3743,
                longitude = 88.1915
            ),
            Monastery(
                id = 5,
                name = "Phodong Monastery",
                location = "Phodong, North Sikkim, India",
                description = "An important Kagyupa monastery founded in the early 18th century near Gangtok. Phodong is known for its preserved Buddhist frescoes, unique architecture, and its association with renowned scholars and visitors.",
                imageUrl = "https://www.trawell.in/admin/images/upload/134108838Phodong_Main.jpg",
                latitude = 27.4925,
                longitude = 89.3631
            ),
            Monastery(
                id = 6,
                name = "Kartok Monastery",
                location = "Yuksom, West Sikkim, India",
                description = "Kartok Monastery is a vibrant red-and-gold shrine by Kartok Lake in Yuksom. Named for one of the three lamas who crowned Sikkim's first king, noted for its peaceful atmosphere and mountain vistas.",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcCpyBT_qd0TNLUvkXUDYu6LJwjtZPXIRNlw&s?w=400",
                latitude = 27.3743,
                longitude = 88.1915
            )
        )
    }
}
