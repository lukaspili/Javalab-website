package seeds;

import java.util.HashSet;
import java.util.Set;

import models.events.Article;
import models.events.Project;
import models.events.Talk;
import models.users.Campus;
import models.users.Profile;
import models.users.Promotion;
import models.users.User;

import org.joda.time.LocalDate;

/**
 * @author Kevin Valfin
 */
public class TestSeed {

    public static void insert() {

        Campus paris = new Campus();
        paris.name = Campus.Name.PARIS;
        paris.save();

        Campus grenoble = new Campus();
        grenoble.name = Campus.Name.GRENOBLE;
        grenoble.save();

        Campus toulouse = new Campus();
        toulouse.name = Campus.Name.TOULOUSE;
        toulouse.save();

        Campus clermont = new Campus();
        clermont.name = Campus.Name.CLERMONTFERRAND;
        clermont.save();

        Campus montpellier = new Campus();
        montpellier.name = Campus.Name.MONTPELLIER;
        montpellier.save();

        User user1 = new User();
        user1.idBooster = "8080";
        user1.firstName = "John";
        user1.lastName = "Doe";
        user1.campus = montpellier;
        user1.promotion = Promotion.B3;
        user1.profile = Profile.MEMBER;
        user1.save();

        User user2 = new User();
        user2.idBooster = "75054";
        user2.firstName = "Lukasz";
        user2.lastName = "Piliszczuk";
        user2.campus = montpellier;
        user2.promotion = Promotion.M1;
        user2.profile = Profile.GLM;
        user2.save();

        User user3 = new User();
        user3.idBooster = "92755";
        user3.firstName = "Kevin";
        user3.lastName = "Valfin";
        user3.campus = paris;
        user3.promotion = Promotion.M1;
        user3.profile = Profile.GLM;
        user3.save();
        
        User user6 = new User();
        user6.idBooster = "78302";
        user6.firstName = "Mathieu";
        user6.lastName = "Gilet";
        user6.campus = paris;
        user6.promotion = Promotion.M2;
        user6.profile = Profile.CLM;
        user6.save();
        
        User user4 = new User();
        user4.idBooster = "123";
        user4.firstName = "Candidate 1";
        user4.lastName = "Foo";
        user4.campus = paris;
        user4.promotion = Promotion.B2;
        user4.profile = Profile.CANDIDATE;
        user4.save();

        User user5 = new User();
        user5.idBooster = "456";
        user5.firstName = "Candidate 2";
        user5.lastName = "Bar";
        user5.campus = paris;
        user5.promotion = Promotion.B1;
        user5.profile = Profile.CANDIDATE;
        user5.save();

        User user51 = new User();
        user51.idBooster = "456";
        user51.firstName = "Candidate 2";
        user51.lastName = "Bar";
        user51.campus = paris;
        user51.promotion = Promotion.B1;
        user51.profile = Profile.CANDIDATE;
        user51.save();

        User user511 = new User();
        user511.idBooster = "45624";
        user511.firstName = "Candidate 3";
        user511.lastName = "Bar";
        user511.campus = montpellier;
        user511.promotion = Promotion.B1;
        user511.profile = Profile.CANDIDATE;
        user511.save();

        User user5111 = new User();
        user5111.idBooster = "456565";
        user5111.firstName = "Candidate 4";
        user5111.lastName = "Bar";
        user5111.campus = montpellier;
        user5111.promotion = Promotion.B1;
        user5111.profile = Profile.MEMBER;
        user5111.save();

        User user51111 = new User();
        user51111.idBooster = "45645";
        user51111.firstName = "Candidate 5";
        user51111.lastName = "Bar";
        user51111.campus = grenoble;
        user51111.promotion = Promotion.B1;
        user51111.profile = Profile.MEMBER;
        user51111.save();

        User user511111 = new User();
        user511111.idBooster = "45612";
        user511111.firstName = "Candidate 6";
        user511111.lastName = "Bar";
        user511111.campus = montpellier;
        user511111.promotion = Promotion.B1;
        user511111.profile = Profile.MEMBER;
        user511111.save();

        User user5111111 = new User();
        user5111111.idBooster = "45634";
        user5111111.firstName = "Candidate 7";
        user5111111.lastName = "Bar";
        user5111111.campus = toulouse;
        user5111111.promotion = Promotion.B1;
        user5111111.profile = Profile.MEMBER;
        user5111111.save();

        User user51111111 = new User();
        user51111111.idBooster = "45667";
        user51111111.firstName = "Candidate 8";
        user51111111.lastName = "Bar";
        user51111111.campus = montpellier;
        user51111111.promotion = Promotion.B1;
        user51111111.profile = Profile.CANDIDATE;
        user51111111.save();

        User user511111111 = new User();
        user511111111.idBooster = "45609";
        user511111111.firstName = "Candidate 9";
        user511111111.lastName = "Bar";
        user511111111.campus = clermont;
        user511111111.promotion = Promotion.B1;
        user511111111.profile = Profile.CANDIDATE;
        user511111111.save();

        User user5111111111 = new User();
        user5111111111.idBooster = "45697";
        user5111111111.firstName = "Candidate 10";
        user5111111111.lastName = "Bar";
        user5111111111.campus = clermont;
        user5111111111.promotion = Promotion.B1;
        user5111111111.profile = Profile.CANDIDATE;
        user5111111111.save();


        Set<User> users = new HashSet<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);


        Talk talk1 = new Talk();
        talk1.title = "Google map with Android";
        talk1.speaker = user6;
        talk1.date = new LocalDate(2011, 12, 23); // december 23, 2011
        talk1.content = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.";
        talk1.iframe = "<iframe width=\"920\" height=\"500\" src=\"http://www.youtube.com/embed/eTWfNdsrC9Q\" frameborder=\"0\" allowfullscreen></iframe>";
        talk1.save();
        
        Talk talk2 = new Talk();
        talk2.title = "Google map with Android";
        talk2.speaker = user6;
        talk2.date = new LocalDate(2011, 12, 23); // december 23, 2011
        talk2.content = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.";
        talk2.iframe = "<iframe width=\"920\" height=\"500\" src=\"http://www.youtube.com/embed/eTWfNdsrC9Q\" frameborder=\"0\" allowfullscreen></iframe>";
        talk2.save();

        Talk talk3 = new Talk();
        talk3.title = "Google map with Android";
        talk3.speaker = user6;
        talk3.date = new LocalDate(2011, 12, 23); // december 23, 2011
        talk3.content = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.";
        talk3.iframe = "<iframe width=\"920\" height=\"500\" src=\"http://www.youtube.com/embed/eTWfNdsrC9Q\" frameborder=\"0\" allowfullscreen></iframe>";
        talk3.save();

        Talk talk4 = new Talk();
        talk4.title = "Google map with Android";
        talk4.speaker = user6;
        talk4.date = new LocalDate(2011, 12, 23); // december 23, 2011
        talk4.content = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.";
        talk4.iframe = "<iframe width=\"920\" height=\"500\" src=\"http://www.youtube.com/embed/eTWfNdsrC9Q\" frameborder=\"0\" allowfullscreen></iframe>";
        talk4.save();

        Talk talk5 = new Talk();
        talk5.title = "Google map with Android";
        talk5.speaker = user6;
        talk5.date = new LocalDate(2011, 12, 23); // december 23, 2011
        talk5.content = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.";
        talk5.iframe = "<iframe width=\"920\" height=\"500\" src=\"http://www.youtube.com/embed/eTWfNdsrC9Q\" frameborder=\"0\" allowfullscreen></iframe>";
        talk5.save();

        Talk talk6 = new Talk();
        talk6.title = "Google map with Android";
        talk6.speaker = user6;
        talk6.date = new LocalDate(2011, 12, 23); // december 23, 2011
        talk6.content = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.";
        talk6.iframe = "<iframe width=\"920\" height=\"500\" src=\"http://www.youtube.com/embed/eTWfNdsrC9Q\" frameborder=\"0\" allowfullscreen></iframe>";
        talk6.save();
        
        Article article1 = new Article();
        article1.title = "Windows 8";
        article1.content = "Windows 8 est sorti depuis une semaine environ et son accueil est plutôt mitigé. Avec cette nouvelle version Microsoft espère améliorer la synergie entre ses différents périphériques : Console de jeux, PC, Mobile et tablette. Pour cela les différentes versions du système partagent une interface commune : Modern UI (anciennement Metro).C'est justement cette nouvelle interface qui suscite pas mal de crainte de la part des utilisateurs PC. En effet, même si Modern UI est très réussie pour une utilisation tactile qu'en est-il pour une utilisation Clavier/Souris ? ";
        article1.creationDate = new LocalDate(2011, 12, 23); // december 23, 2011
        article1.author = user3;
        article1.save();
        
        Article article2 = new Article();
        article2.title = "Desktopography 2012";
        article2.content = "Comme chaque année Desktopography propose une sélection de fonds d'écran autour du thème de la nature. Les designers et les développeurs passent près de 90% de leur temps devant un ordinateur donc les fonds d'écrans les plus attrayants sont ceux qui mélangent de magnifique design tout en donnant l'impression d'être à l'extérieur. Cette année le site fait peau neuve et propose des créations toujours aussi travaillées et originales. Avec près de 60 images vous aurez de quoi amener un brin de nature à votre bureau.";
        article2.creationDate = new LocalDate(2012, 07, 10); // december 23, 2011
        article2.author = user3;
        article2.save();

        Article article3 = new Article();
        article3.title = "Sur France Culture, on conseille d’éviter la banlieue après 16 heures";
        article3.content = "\n" +
                "\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam bibendum hendrerit neque, eget rutrum nibh auctor vitae. In iaculis, elit sit amet pulvinar lobortis, libero lectus tempus ante, ac vestibulum urna ipsum at est. Donec consequat lorem sit amet arcu tempus tempus ut sit amet ante. Duis eu erat neque. Curabitur nisl tellus, varius non pretium et, sollicitudin in ligula. Quisque congue erat a magna volutpat non congue mi auctor. Aenean tempus mattis accumsan.\n" +
                "\n" +
                "Morbi sagittis magna gravida lacus elementum vehicula. Mauris nec massa orci, et ultricies lectus. Nunc eleifend, ante quis interdum pharetra, nunc nunc vestibulum nisi, quis pretium quam mauris vitae sapien. Aenean blandit, turpis sagittis sodales gravida, felis dui fringilla augue, at consequat nulla tortor in enim. Morbi nibh erat, molestie nec faucibus eget, facilisis in lacus. Aliquam tincidunt lacus vitae nulla semper vulputate. Ut quis mauris vitae eros commodo auctor non sit amet nisi. Nam nec sagittis velit. Aenean facilisis, ante sit amet iaculis dignissim, arcu odio porta lacus, ut tincidunt velit felis ut libero. Morbi varius metus id nulla sodales rutrum imperdiet tellus hendrerit. Ut ac metus eu massa fringilla accumsan ac nec tortor. Nulla placerat ligula eget risus ullamcorper vehicula. In sit amet risus dolor, vitae dapibus nisl.\n" +
                "\n" +
                "Donec tincidunt eleifend justo, vel volutpat nulla laoreet eu. Nam placerat dui non urna venenatis vestibulum. Etiam porta, ipsum in dignissim pharetra, erat justo bibendum mauris, non rhoncus odio ligula vel purus. Phasellus tortor purus, facilisis vitae dapibus id, egestas vel ipsum. Fusce rutrum pellentesque massa, id aliquet dui feugiat a. Donec a elit metus, nec suscipit nibh. Suspendisse ut arcu odio, accumsan elementum lorem. Phasellus non lacus augue. Mauris in est convallis mauris ornare adipiscing sit amet at arcu.\n" +
                "\n" +
                "Donec non dui ligula. Proin pharetra ultricies nisi at pretium. Quisque scelerisque adipiscing lobortis. Morbi erat turpis, pretium et tristique et, varius sed augue. In malesuada sagittis libero sit amet aliquam. Phasellus vestibulum varius risus, in varius est eleifend quis. Pellentesque fermentum faucibus enim quis dictum. Donec vel ipsum libero.\n" +
                "\n" +
                "Integer id lacus quis est congue condimentum quis id sapien. Cras nec tortor sem. Donec cursus pulvinar leo, in ultricies ligula ultricies in. Integer imperdiet, lectus eget sollicitudin pharetra, velit nisl posuere nisl, bibendum ultricies nisl ante ornare justo. Morbi leo leo, vulputate ut consectetur sed, commodo nec ipsum. Integer libero urna, accumsan a laoreet in, convallis id odio. Ut pellentesque massa sed est mattis non suscipit sapien convallis. Phasellus sit amet dui non lacus adipiscing suscipit. Cras id imperdiet orci. Duis tincidunt massa suscipit dui ornare fermentum. ";
        article3.creationDate = new LocalDate(2012, 8, 22); // december 23, 2011
        article3.author = user6;
        article3.save();

        Article article4 = new Article();
        article4.title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam bibendum hendrerit neque, eget rutrum nibh auctor vitae.";
        article4.content = "\n" +
                "\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque porta pretium dolor, quis ultricies sapien feugiat sit amet. Nam et lacus metus, vitae luctus urna. Donec felis velit, pharetra id tincidunt sed, facilisis vel mauris. Vestibulum porttitor cursus facilisis. Quisque ut elit sem. Fusce sit amet risus quam, sit amet malesuada lectus. Nam rhoncus porta purus quis semper. Aenean congue nulla mattis erat semper in lacinia purus aliquet. Cras sit amet tortor ut justo consequat aliquam eu ut odio. Aliquam metus libero, ullamcorper eu semper a, vestibulum id dui. Nulla facilisi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Curabitur lorem eros, tincidunt ut ullamcorper a, lobortis sit amet elit. Ut augue magna, condimentum ultricies cursus vitae, tincidunt quis nisi. Etiam felis nisi, congue vel facilisis eget, varius sit amet nisi. Cras nec vulputate nisi.\n" +
                "\n" +
                "Sed posuere faucibus semper. Praesent commodo sem ac ligula posuere hendrerit. Quisque quis eros tellus, sed fringilla libero. Mauris nisl orci, adipiscing sit amet convallis nec, mollis non nibh. Mauris ornare tincidunt tortor, elementum tristique purus rhoncus a. Proin placerat molestie feugiat. Donec id dolor quam, at placerat metus. Praesent varius tincidunt fringilla. Nunc sapien arcu, suscipit ut dapibus id, vulputate at metus. Nullam a neque ante, non adipiscing turpis. Phasellus sit amet risus nisl, in mollis eros.\n" +
                "\n" +
                "Praesent vel velit diam. Mauris faucibus varius ornare. Vestibulum condimentum bibendum quam, nec viverra neque gravida quis. Integer quis nibh justo. Praesent dolor orci, luctus id ultricies quis, sollicitudin eget turpis. Duis semper magna eu odio feugiat ullamcorper. In hac habitasse platea dictumst. Pellentesque adipiscing vulputate mauris, vehicula porttitor massa suscipit ornare. Sed luctus arcu id est bibendum suscipit. Maecenas ut risus et ante facilisis faucibus ac ut felis. Donec porttitor scelerisque neque, sed sodales mi pretium in. In adipiscing ullamcorper sapien at dictum. Ut bibendum eleifend odio et commodo.\n" +
                "\n" +
                "Sed commodo, purus venenatis consectetur venenatis, ante dolor eleifend lectus, id tempus arcu purus at erat. Nulla facilisi. Proin venenatis tincidunt sagittis. Vivamus consequat posuere urna in vestibulum. Vivamus ullamcorper ante ac est pellentesque ac mollis magna hendrerit. Sed interdum nisi eu orci dapibus vestibulum. Fusce nibh turpis, hendrerit in pretium viverra, iaculis vitae magna. Curabitur quis sem ac nunc tincidunt condimentum. Nam consectetur, purus sit amet porttitor posuere, nulla metus pulvinar tellus, at pellentesque quam enim eu arcu. Nunc ut nunc in velit eleifend porttitor tristique vel ligula. Donec eget mauris metus, a mollis nisi. Mauris adipiscing placerat porttitor. Cras eget justo vel velit commodo accumsan. Sed cursus, augue non consequat pretium, metus magna condimentum mi, eu accumsan velit dui varius urna. Nunc eros tellus, ultrices vitae rhoncus vitae, bibendum ac elit. Quisque magna lacus, luctus eu lacinia id, consectetur nec ipsum.\n" +
                "\n" +
                "Proin euismod, nisi ac suscipit posuere, nisl dui aliquam diam, vitae interdum sem nisl pulvinar dui. Phasellus vel est odio. Fusce feugiat laoreet urna, eu mattis velit rutrum non. Nullam arcu enim, varius et lobortis nec, iaculis vel arcu. Fusce hendrerit quam at dui fermentum quis dictum leo consequat. Sed iaculis luctus urna, vel cursus neque sagittis eget. Donec cursus augue at justo laoreet adipiscing. Fusce tempus, turpis id tempor gravida, lectus sapien placerat enim, ut auctor enim sem sit amet eros. Maecenas quis odio velit. Suspendisse tortor metus, tristique mattis aliquet vel, gravida eget ligula. Nullam luctus commodo nunc, et sollicitudin odio fermentum et. Nullam in arcu et justo lacinia euismod. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Quisque id arcu et lacus scelerisque tempor. In hac habitasse platea dictumst. Praesent et porta nibh.\n" +
                "\n" +
                "Phasellus id risus tortor. Aenean pulvinar magna quis ligula porta eleifend. Duis scelerisque faucibus metus et mollis. Morbi eleifend erat mollis justo luctus pellentesque. Nullam iaculis posuere leo vitae auctor. Nullam cursus risus sed leo consectetur at porttitor quam posuere. Integer sit amet arcu ac turpis fermentum lobortis. Curabitur erat justo, ultricies mollis posuere nec, blandit quis enim. In dictum commodo erat, vel feugiat velit aliquam a. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Praesent vel leo a velit rhoncus ullamcorper semper sed est. Proin adipiscing risus eu metus rutrum in tempor sapien porta. Aliquam libero metus, euismod in mattis at, vehicula sit amet purus. Quisque rhoncus, risus sit amet pulvinar imperdiet, sapien arcu vulputate dui, eu pretium lacus tellus ac ligula. Phasellus cursus aliquam tortor, et porttitor arcu eleifend vel.\n" +
                "\n" +
                "Etiam malesuada tempor ligula sit amet auctor. Integer rhoncus euismod egestas. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce ac est lorem, eu porttitor eros. Donec elementum sagittis ornare. Aliquam euismod convallis nunc adipiscing tempus. Aenean sed metus id diam iaculis posuere non eu ipsum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed faucibus, nisi sed volutpat faucibus, ligula lacus adipiscing sapien, vel volutpat diam purus sit amet purus. Vestibulum nec sem risus, quis commodo justo. Quisque placerat vestibulum elit, id consectetur enim viverra id. Phasellus rutrum augue nec tortor pulvinar fermentum fringilla dui feugiat. Vivamus turpis libero, rhoncus non tincidunt sed, dictum ac lectus. Phasellus diam lacus, molestie a luctus ut, imperdiet eu nunc.\n" +
                "\n" +
                "Sed id ligula ac lacus tincidunt auctor a non ante. Quisque vulputate ligula at turpis varius eget consequat arcu lobortis. In nec nibh pharetra turpis ultrices feugiat. Curabitur condimentum tortor id orci gravida pulvinar. Morbi nec convallis ante. Phasellus ac mauris tellus, eget facilisis quam. Nulla sodales, nisi in aliquam venenatis, arcu arcu vulputate ligula, sit amet molestie est diam vitae nisl. Fusce nunc ante, porta eu aliquet in, tincidunt in dolor. Quisque turpis dolor, semper quis sagittis eget, rhoncus ac nisl.\n" +
                "\n" +
                "Suspendisse a ipsum nec velit lacinia porta. Integer justo lorem, tempor id ullamcorper quis, ornare vel metus. Quisque vel lorem a augue porttitor ultricies. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean porttitor erat quis nunc lobortis fermentum. Maecenas et condimentum lectus. Duis cursus, lacus id accumsan interdum, mauris dui elementum neque, sit amet ultricies neque dolor eget nisi. Curabitur posuere magna vel augue gravida vestibulum. Nulla facilisi. Pellentesque non ultricies mauris. Sed tincidunt elit ac arcu gravida id dignissim nisl sollicitudin.\n" +
                "\n" +
                "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Mauris lorem arcu, convallis non rutrum at, pretium sed massa. Aliquam bibendum purus ac sapien euismod ac adipiscing leo pretium. Phasellus condimentum feugiat magna, ac tempor eros blandit vel. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. In ullamcorper feugiat libero, sit amet facilisis risus aliquam vel. Suspendisse velit nisi, vestibulum ac facilisis sed, eleifend et est. In eget justo augue. Cras lobortis, libero quis adipiscing dignissim, risus mauris consequat eros, a congue purus purus vitae turpis. Morbi in posuere velit. Morbi convallis turpis ut ante commodo fringilla. Phasellus libero arcu, sodales sit amet vehicula imperdiet, imperdiet sit amet nibh. Donec mollis pulvinar accumsan. Cras id sem turpis. Duis sed dolor nisi, eget posuere ante. In hac habitasse platea dictumst. ";
        article4.creationDate = new LocalDate(2012, 10, 22); // december 23, 2011
        article4.author = user6;
        article4.save();

        Article article5 = new Article();
        article5.title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        article5.content = "\n" +
                "\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla et nibh dui, id porta ipsum. Sed sed augue nulla. Aenean consectetur velit nibh. Nullam magna nisi, blandit eget luctus eget, malesuada eu massa. Sed sodales orci id arcu consectetur et tincidunt lorem egestas. Vivamus eget rutrum nisi. Morbi semper lectus vel sem ornare ullamcorper. Ut et arcu sed orci rhoncus mattis. Praesent porta ullamcorper est, ut tincidunt lectus rhoncus ut. Quisque eu leo id neque laoreet volutpat tristique aliquet urna. Integer consequat sollicitudin sapien, nec tempor enim convallis in. Curabitur porta elit ac purus pharetra quis sodales nunc consectetur. Nulla ante massa, blandit volutpat adipiscing eget, facilisis quis ipsum. Morbi tincidunt felis laoreet urna tempor mollis. Suspendisse ut nunc nisl, id fermentum eros.\n" +
                "\n" +
                "In dictum, tortor sit amet lacinia gravida, mi tortor lacinia dolor, in pulvinar erat dui ac dui. Sed ac justo eros. Maecenas rhoncus leo a mauris lacinia aliquet. Maecenas felis augue, placerat quis convallis in, pulvinar vel turpis. Vivamus tincidunt accumsan ullamcorper. Nam et dignissim metus. Etiam a mauris massa, vitae ornare ipsum. Morbi erat elit, volutpat quis sollicitudin sed, tempor vitae tellus. Nunc in augue tristique neque elementum placerat.\n" +
                "\n" +
                "Ut rutrum fermentum euismod. Nunc ipsum mauris, dapibus eu posuere vel, viverra et erat. Pellentesque sem metus, accumsan sed consequat in, pharetra ut turpis. Curabitur dolor mauris, volutpat ac lacinia vel, sagittis vel dolor. Mauris ut risus nisl. Sed condimentum convallis mollis. Curabitur feugiat velit eget velit congue non cursus quam gravida. Donec ullamcorper sem sed est adipiscing et semper tortor lacinia. Sed eget turpis ut nibh tristique lobortis ut in nulla. Nullam at enim purus, nec aliquam ipsum. Pellentesque tempus consectetur tellus id pretium. In vitae fringilla orci. Aliquam vestibulum justo sit amet tellus pharetra auctor. Nam quis lorem nec turpis facilisis sagittis a sit amet purus. Integer nisi leo, pharetra sit amet congue ac, rhoncus sed augue. Etiam eu nulla mi, a porttitor justo.\n" +
                "\n" +
                "Vestibulum vel purus nec tellus mollis pellentesque. Aliquam mattis, massa eget lobortis lacinia, nisi orci accumsan dolor, ac facilisis ante urna quis augue. Mauris risus diam, lobortis eget mattis vel, faucibus in tellus. Suspendisse quis sapien vel ligula euismod consectetur vel ac augue. Integer faucibus dolor vel metus placerat eget tincidunt est bibendum. Etiam in tortor lacus. Aenean consectetur eleifend nulla, ac tincidunt sem suscipit nec. Integer ut augue sapien, quis pulvinar purus. In a urna ut ligula scelerisque molestie. Donec ac justo in ante fringilla vehicula tincidunt ut augue. Suspendisse ultricies, orci vel condimentum varius, massa quam iaculis eros, non vestibulum velit sem euismod sem. Ut facilisis, ipsum vel tristique iaculis, sem urna rhoncus mi, non lacinia justo erat a velit. Vestibulum ut nisi tortor. Suspendisse id ipsum orci, non luctus odio. Pellentesque et posuere augue.\n" +
                "\n" +
                "Donec at sem nec magna cursus faucibus. Sed gravida, ligula vel elementum mollis, nunc lorem porttitor odio, ut accumsan lectus turpis eu sem. Cras erat augue, tristique eget pellentesque sit amet, sagittis volutpat augue. Quisque ornare tellus arcu. Aenean feugiat facilisis nisi, id aliquet purus facilisis eget. Integer mauris purus, rhoncus eget lobortis et, blandit in libero. Sed enim lectus, gravida sed varius sit amet, sodales eget felis. Morbi elementum velit non lacus scelerisque pulvinar. Nam vel risus tincidunt diam rhoncus suscipit vel eget urna.\n" +
                "\n" +
                "Nunc rhoncus est mauris, consequat elementum risus. Duis dictum erat sit amet erat dapibus sit amet blandit felis dignissim. Morbi eleifend mi ac libero fringilla id tempus tellus consequat. Phasellus pharetra gravida lacus non tempus. Sed nunc dolor, fermentum nec facilisis non, malesuada quis odio. Phasellus ullamcorper blandit ipsum, quis vestibulum dolor molestie vel. Mauris massa nisi, facilisis in consectetur nec, feugiat vel nunc.\n" +
                "\n" +
                "Fusce elit ipsum, dapibus at mattis in, iaculis nec mauris. Aliquam at nulla id lorem consectetur pulvinar et nec nibh. Fusce et turpis velit. Phasellus dui lorem, bibendum ac condimentum in, vulputate et massa. Fusce et egestas mauris. In ac erat eget ante adipiscing aliquet sit amet sit amet turpis. Aliquam erat volutpat. Nunc ac luctus nulla. Sed tincidunt arcu a arcu posuere non luctus sapien scelerisque. Nulla non metus purus. Maecenas ut mi non enim vestibulum scelerisque.\n" +
                "\n" +
                "Nulla et sem ipsum, quis rhoncus libero. Praesent et ligula lacus. Donec mattis erat ut neque bibendum in blandit ligula tristique. Morbi varius, libero vel condimentum consequat, nisi dui iaculis massa, vel adipiscing dolor dui vulputate sapien. Aliquam at ipsum eget quam iaculis ullamcorper non nec risus. Donec pulvinar, sapien quis accumsan porta, nisl dui luctus quam, a porta urna quam vel orci. Aliquam placerat elementum arcu ut egestas. Duis ligula dolor, faucibus et auctor eu, varius at mauris. Mauris diam risus, pellentesque sit amet vestibulum eget, malesuada non justo.\n" +
                "\n" +
                "Etiam ultrices gravida nulla sit amet euismod. In laoreet, ante nec dignissim fringilla, leo elit sollicitudin leo, ac tristique turpis urna eu purus. Nullam eget orci orci, mollis egestas eros. Nulla laoreet erat eget ligula volutpat faucibus. Morbi quis varius purus. Suspendisse quis convallis dui. Fusce dapibus sollicitudin ultricies. Suspendisse non turpis eget urna molestie lobortis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.\n" +
                "\n" +
                "Curabitur et nibh eget odio fermentum fringilla eget ac purus. Praesent rutrum, justo a convallis tempus, orci nulla vulputate mi, id placerat metus felis vitae nisl. Suspendisse vel magna quis sem facilisis sodales vitae in mi. Nulla ut ligula tellus, ut luctus nulla. Donec pretium felis in neque semper semper. Nullam vulputate consequat enim, at lacinia sapien ultrices eget. Sed sapien felis, auctor sed auctor at, adipiscing sit amet dui. Ut non ipsum nisl. Quisque tincidunt scelerisque dolor, in venenatis sem ultrices ac. Curabitur cursus ultricies massa vitae scelerisque. Mauris id lectus dolor, ut bibendum ipsum. Vivamus tincidunt metus sed sapien cursus pulvinar. Mauris aliquet scelerisque dui blandit mollis. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Praesent nec enim turpis.\n" +
                "\n" +
                "Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Proin vulputate odio nisi. Vivamus turpis quam, faucibus sit amet tristique et, tempor eu elit. Integer accumsan ante in risus iaculis id rhoncus quam bibendum. Donec lobortis tempus dui, id dictum diam sodales at. Nulla orci quam, vehicula id mollis non, auctor non ante. Cras orci quam, hendrerit eget blandit id, pulvinar et velit. Donec eu lectus enim, eu ullamcorper urna.\n" +
                "\n" +
                "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nam ut risus vel neque consectetur luctus. Ut eleifend pharetra porta. Cras condimentum tellus sit amet felis varius sit amet pretium tortor sodales. Morbi id ornare mauris. Mauris malesuada nisl sit amet dui interdum ac luctus elit convallis. Quisque non eros nec ligula consequat porta. Quisque enim diam, lacinia hendrerit malesuada id, pellentesque nec eros. Vivamus iaculis, orci sed feugiat tincidunt, sapien neque hendrerit augue, in fermentum tellus risus eu sem.\n" +
                "\n" +
                "Cras eget libero sit amet dui vestibulum molestie at vitae eros. Integer convallis consequat nisl at volutpat. Proin mollis quam quis metus pretium non interdum purus consequat. Duis quis vehicula diam. Aliquam convallis tincidunt faucibus. Curabitur accumsan risus a nunc sodales adipiscing lacinia enim euismod. Morbi cursus blandit vulputate. Mauris elit lorem, consectetur ut tempus nec, dapibus vel turpis. Nullam dignissim blandit faucibus. Vivamus purus risus, malesuada at semper quis, bibendum id lorem. Etiam ac diam ac elit porttitor tempor. Aliquam ultricies neque in justo tempor dignissim.\n" +
                "\n" +
                "Vivamus sed mi sagittis quam accumsan interdum nec quis est. Vivamus vehicula congue diam. Vivamus faucibus magna quis felis auctor ut convallis felis elementum. Donec gravida quam vel orci gravida sollicitudin. Nunc ornare tempor porta. Donec vitae felis velit, vel pretium erat. Praesent nec ultrices purus. Vivamus sapien nisi, eleifend at vehicula nec, ullamcorper ut dolor. Donec tristique, dolor in suscipit tincidunt, velit dui aliquam nulla, ac pretium erat augue mattis mauris. Etiam euismod lacinia augue, eu accumsan ante consequat eleifend. Maecenas a egestas nibh. Nam placerat tellus ac metus blandit id pellentesque lacus eleifend. Nam facilisis luctus euismod. Ut id nisl vestibulum ipsum condimentum molestie.\n" +
                "\n" +
                "Integer a turpis non elit convallis bibendum ut ut ante. Suspendisse tellus massa, fringilla vel sodales ac, egestas ut lorem. Quisque odio ligula, sollicitudin vitae posuere id, feugiat et nisl. Cras sed urna neque. Nullam quis sapien ligula. Aliquam vitae sapien tellus, eu molestie nunc. Fusce hendrerit mi sed ligula facilisis eget adipiscing sem gravida. Aliquam bibendum nibh in enim molestie rhoncus. Phasellus tempor vehicula justo, sed consequat elit gravida ac. Sed nunc dui, tincidunt ornare fringilla non, aliquet at erat. Pellentesque nisi tellus, mattis vel varius eget, fermentum eget nisi. Morbi a faucibus sapien. Donec ultricies mattis dolor, ac aliquam ante accumsan a. ";
        article5.creationDate = new LocalDate(2012, 11, 22); // december 23, 2011
        article5.author = user6;
        article5.save();

        Project project1 = new Project();
        project1.name = "Java lab. website";
        project1.description = "The new website for the lab";
        project1.members = users;
        project1.campus = paris;
        project1.save();
        
        Project SupinfoMarks = new Project(); 
        SupinfoMarks.name = "Supinfo Marks";
        SupinfoMarks.description = "Un projet du Labo java";
        SupinfoMarks.members = users;
        SupinfoMarks.campus = paris;
        SupinfoMarks.save();
    }
}
