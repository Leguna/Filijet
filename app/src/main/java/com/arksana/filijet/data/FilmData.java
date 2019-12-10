package com.arksana.filijet.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class FilmData implements Parcelable {

    private static ArrayList<Film> films = new ArrayList<>();


    private static String[][] data;
    private static String[][] data2;

    static {
        data = new String[][]{
                {
                        "Leguna, Storyboard and Film Director",
                        "July 01, 2000",
                        "Girl Under the Ketapang Tree",
                        "100",
                        "This film is adapted from a novel by Leguna. With a love for peace, the film Agung the main character is struggling to solve problems that are not normal. Together with a bully girl who had not been known for a long time, Agung found victory, and revealed a bloody mystery at his school.\n",
                        "https://i.ibb.co/pZ5hNRK/poster-gadispohonketapang.png",
                        "https://www.themoviedb.org/movie/627222-gadis-bawah-pohon-ketapang?language=en-US"
                },
                {
                        "M. Night Shyamalan, Director",
                        "January 16, 2019",
                        "Glass",
                        "65",
                        "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg",
                        "https://www.themoviedb.org/movie/450465-glass?language=en-US"
                },
                {
                        "Donovan Marsh, Director",
                        "October 19, 2018",
                        "Hunter Killer",
                        "63",
                        "Captain Glass of the USS Arkansas discovers that a coup d'état is taking place in Russia, so he and his crew join an elite group working on the ground to prevent a war.\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/a0j18XNVhP4RcW3wXwsqT0kVoQm.jpg",
                        "https://www.themoviedb.org/movie/399402-hunter-killer?language=en-US"
                },
                {
                        "Rob Marshall, Director",
                        "December 13, 2018",
                        "Mary Poppins Returns",
                        "66",
                        "In Depression-era London, a now-grown Jane and Michael Banks, along with Michael's three children, are visited by the enigmatic Mary Poppins following a personal loss. Through her unique magical skills, and with the aid of her friend Jack, she helps the family rediscover the joy and wonder missing in their lives.\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/uTVGku4LibMGyKgQvjBtv3OYfAX.jpg",
                        "https://www.themoviedb.org/movie/400650-mary-poppins-returns?language=en-US"
                },
                {
                        "Christian Rivers, Director",
                        "November 27, 2018",
                        "Mortal Engines",
                        "60",
                        "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/gLhYg9NIvIPKVRTtvzCWnp1qJWG.jpg",
                        "https://www.themoviedb.org/movie/428078-mortal-engines?language=en-US"
                },

                {
                        "Aris Nugraha, Director",
                        "January 17, 2019",
                        "Retired Thug",
                        "65",
                        "After three years, the business of Muslihat (Epi Kusnandar), who has retired as a thug, has a problem. Sales decline. Muslihat also faces new problems when Safira (Safira Maharani), her only daughter, grows up in adolescence and begins to be visited by boys. A bigger problem: frictions between his former subordinates.\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/7xfWyoz4SF5LHZ713eMtC2aZ0lT.jpg",
                        "https://www.themoviedb.org/movie/568709-preman-pensiun?language=en-US"
                },
                {
                        "Ben Chandler, Screenplay and Story",
                        "November 21, 2018",
                        "Robin Hood",
                        "58",
                        "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/AiRfixFcfTkNbn2A73qVJPlpkUo.jpg",
                        "https://www.themoviedb.org/movie/375588-robin-hood-origins?language=en-US"
                },
                {
                        "Rodney Rothman, Director and Screenplay",
                        "December 6, 2018",
                        "Spider-Man: Into the Spider-Verse",
                        "84",
                        "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg",
                        "https://www.themoviedb.org/movie/324857-spider-man-into-the-spider-verse?language=en-US"
                },
                {
                        "Fede Alvarez, Director and Screenplay",
                        "October 25, 2018",
                        "The Girl in the Spider's Web",
                        "60",
                        "In Stockholm, Sweden, hacker Lisbeth Salander is hired by Frans Balder, a computer engineer, to retrieve a program that he believes it is too dangerous to exist.\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/w4ibr8R702DCjwYniry1D1XwQXj.jpg",
                        "https://www.themoviedb.org/movie/446807-the-girl-in-the-spider-s-web?language=en-US"
                },
                {
                        "Clint Eastwood, Director",
                        "December 14, 2018",
                        "The Mule",
                        "66",
                        "Earl Stone, a man in his 80s, is broke, alone, and facing foreclosure of his business when he is offered a job that simply requires him to drive. Easy enough, but, unbeknownst to Earl, he’s just signed on as a drug courier for a Mexican cartel. He does so well that his cargo increases exponentially, and Earl hit the radar of hard-charging DEA agent Colin Bates.\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/klazQbxk3yfuZ8JcfO9jdKOZQJ7.jpg",
                        "https://www.themoviedb.org/movie/504172-the-mule?language=en-US"
                },
                {
                        "Mike Zeck, Characters",
                        "September 28, 2018",
                        "Venom",
                        "66",
                        "Investigative journalist Eddie Brock attempts a comeback following a scandal, but accidentally becomes the host of Venom, a violent, super powerful alien symbiote. Soon, he must rely on his newfound powers to protect the world from a shadowy organization looking for a symbiote of their own.\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg",
                        "https://www.themoviedb.org/movie/335983-venom?language=en-US"
                }

        };
        data2 = new String[][]{
                {
                        "Greg Berlanti, Creator",
                        "October 10, 2012",
                        "Arrow",
                        "58",
                        "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg",
                        "https://www.themoviedb.org/tv/1412-arrow?language=en-US"
                },
                {
                        "Geoff Johns, Creator",
                        "October 7, 2014",
                        "The Flash",
                        "66",
                        "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/fki3kBlwJzFp8QohL43g9ReV455.jpg",
                        "https://www.themoviedb.org/tv/60735-the-flash?language=en-US"
                },
                {
                        "Seth MacFarlane, Creator",
                        "January 31, 1999   ",
                        "Family Guy",
                        "65",
                        "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/gBGUL1UTUNmdRQT8gA1LUV4yg39.jpg",
                        "https://www.themoviedb.org/tv/1434-family-guy?language=en-US"
                },
                {
                        "Dave Erickson, Creator",
                        "August 23, 2015",
                        "Fear the Walking Dead",
                        "63",
                        "What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/lZMb3R3e5vqukPbeDMeyYGf2ZNG.jpg",
                        "https://www.themoviedb.org/tv/62286-fear-the-walking-dead?language=en-US"
                },
                {
                        "Brian Yorkey, Creator",
                        "March 31, 2017",
                        "13 Reasons Why",
                        "71",
                        "After a teenage girl's perplexing suicide, a classmate receives a series of tapes that unravel the mystery of her tragic choice.\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/nel144y4dIOdFFid6twN5mAX9Yd.jpg",
                        "https://www.themoviedb.org/tv/66788-13-reasons-why?language=en-US"
                },
                {
                        "Shonda Rhimes, Creator",
                        "March 27, 2005",
                        "Grey's Anatomy",
                        "63",
                        "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/eqgIOObafPJitt8JNh1LuO2fvqu.jpg",
                        "https://www.themoviedb.org/tv/1416-grey-s-anatomy?language=en-US"
                },
                {
                        "Bruno Heller, Creator",
                        "September 22, 2014",
                        "Gotham",
                        "69",
                        "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/4XddcRDtnNjYmLRMYpbrhFxsbuq.jpg",
                        "https://www.themoviedb.org/tv/60708-gotham?language=en-US"
                },
                {
                        "Matt Groening, Creator",
                        "December 17, 1989",
                        "The Simpsons",
                        "71",
                        "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/yTZQkSsxUFJZJe67IenRM0AEklc.jpg",
                        "https://www.themoviedb.org/tv/456-the-simpsons?language=en-US"
                },
                {
                        "Masashi Kishimoto, Story",
                        "February 15, 2007",
                        "Naruto Shippūden",
                        "76",
                        "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/zAYRe2bJxpWTVrwwmBc00VFkAf4.jpg",
                        "https://www.themoviedb.org/tv/31910-naruto-shipp-den?language=en-US"
                },
                {
                        "Yabako Sandrovich, Story",
                        "July 31, 2019",
                        "Kengan Ashura",
                        "70",
                        "Since the Edo periods of Japan, gladiator arenas exist in certain areas. In these arenas, wealthy business owners and merchants hire gladiators to fight in unarmed combat where winner takes all. Toki Taouma, nicknamed \"Ashura,\" joins these arenas and devastates his opponents. His spectacular ability to crush his enemies catches the attention of the big business owners, including the Nogi Group chairman, Nogi Hideki.\n",
                        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/u6MpSEaUlDEv7KCn49aHhtTQ43L.jpg",
                        "https://www.themoviedb.org/tv/90660-kengan-ashura?language=en-US"
                },

        };
    }

    public static ArrayList<Film> getListData(int jenis) {
        films = new ArrayList<>();
        for (String[] aData : ((jenis == 1) ? data : data2)) {
            Film film = new Film();
            film.setJudul(aData[2]);
            film.setOverview(aData[4]);
            film.setPhoto(aData[5]);
            film.setRating(aData[3]);
            film.setTanggal(aData[1]);
            film.setUrl(aData[6]);
            films.add(film);
        }
        return films;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }


    protected FilmData(Parcel in) {
    }

    public static final Creator<FilmData> CREATOR = new Creator<FilmData>() {
        @Override
        public FilmData createFromParcel(Parcel in) {
            return new FilmData(in);
        }

        @Override
        public FilmData[] newArray(int size) {
            return new FilmData[size];
        }
    };

}
