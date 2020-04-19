package com.kgaisin.webapp;

import com.kgaisin.webapp.model.*;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ResumeTestData {

    private static final Logger LOG = Logger.getLogger(ResumeTestData.class.getName());

    public static void main(String[] args) {
        String name = "Григорий Кислин";
        Map<ContactType, Contact> contacts = new EnumMap<>(ContactType.class);
        Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

        List<String> achievements = Arrays.asList("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        List<String> qualifications = Arrays.asList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle",
                "MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy",
                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts",
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)",
                "Python: Django",
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js",
                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT",
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix",
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer",
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования",
                "Родной русский, английский \"upper intermediate\"");

        Occupation javaOpsJob = new Occupation(new Link("Java Online Projects", "http://javaops.ru/"),
                YearMonth.of(2013, 10),
                YearMonth.now(),
                new TextSection("Автор проекта",
                        "Создание, организация и проведение Java онлайн проектов и стажировок."));
        Occupation wrikeJob = new Occupation(new Link("Wrike", "https://www.wrike.com/"),
                YearMonth.of(2014, 10),
                YearMonth.of(2016, 1),
                new TextSection("Старший разработчик (backend)",
                        "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
        Occupation ritCenterJob = new Occupation(new Link("RIT Center", null),
                YearMonth.of(2012, 4),
                YearMonth.of(2014, 10),
                new TextSection("Java архитектор",
                        "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"));
        Occupation luxoftJob = new Occupation(new Link("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/"),
                YearMonth.of(2010, 12),
                YearMonth.of(2012, 4),
                new TextSection("Ведущий программист",
                        "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."));
        Occupation yotaJob = new Occupation(new Link("Yota", "https://www.yota.ru/"),
                YearMonth.of(2008, 6),
                YearMonth.of(2010, 12),
                new TextSection("Ведущий специалист",
                        "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"));
        Occupation enkataJob = new Occupation(new Link("Enkata", "http://enkata.com/"),
                YearMonth.of(2007, 3),
                YearMonth.of(2008, 6),
                new TextSection("Разработчик ПО",
                        "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."));
        Occupation siemensAGJob = new Occupation(new Link("Siemens AG", "https://www.siemens.com/ru/ru/home.html"),
                YearMonth.of(2005, 1),
                YearMonth.of(2007, 3),
                new TextSection("Разработчик ПО",
                        "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."));
        Occupation alcatelJob = new Occupation(new Link("Alcatel", "http://www.alcatel.ru/"),
                YearMonth.of(1997, 9),
                YearMonth.of(2005, 1),
                new TextSection("Инженер по аппаратному и программному тестированию",
                        "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."));


        List<Occupation> jobs = Arrays.asList(javaOpsJob, wrikeJob, ritCenterJob, luxoftJob, yotaJob, enkataJob, siemensAGJob, alcatelJob);

        Occupation courseraCourse = new Occupation(new Link("Coursera", "https://www.coursera.org/learn/progfun1"),
                YearMonth.of(2013, 3),
                YearMonth.of(2013, 5),
                new TextSection("\"Functional Programming Principles in Scala\" by Martin Odersky"));
        Occupation luxoftCourse = new Occupation(new Link("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366"),
                YearMonth.of(2011, 3),
                YearMonth.of(2011, 4),
                new TextSection("Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\""));
        Occupation siemensAGCourse = new Occupation(new Link("Siemens AG", "http://www.siemens.ru/"),
                YearMonth.of(2005, 1),
                YearMonth.of(2005, 4),
                new TextSection("3 месяца обучения мобильным IN сетям (Берлин)"));
        Occupation alcatelCourse = new Occupation(new Link("Alcatel", "http://www.alcatel.ru/"),
                YearMonth.of(1997, 9),
                YearMonth.of(1998, 3),
                new TextSection("6 месяцев обучения цифровым телефонным сетям (Москва)"));
        Occupation university = new Occupation(new Link("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/"),
                YearMonth.of(1987, 9),
                YearMonth.of(1993, 7),
                new TextSection("Инженер (программист Fortran, C)"));
        Occupation universityGrad = new Occupation(new Link("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/"),
                YearMonth.of(1993, 9),
                YearMonth.of(1996, 7),
                new TextSection("Аспирантура (программист С, С++)"));
        Occupation school = new Occupation(new Link("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/"),
                YearMonth.of(1984, 9),
                YearMonth.of(1987, 6),
                new TextSection("Закончил с отличием"));
        List<Occupation> education = Arrays.asList(courseraCourse, luxoftCourse, siemensAGCourse, alcatelCourse, university, universityGrad, school);

        contacts.put(ContactType.MOBILE_PHONE, new Contact("+7(921) 855-0482"));
        contacts.put(ContactType.SKYPE, new Contact(new Link("grigory.kislin", "skype:grigory.kislin")));
        contacts.put(ContactType.EMAIL, new Contact(new Link("gkislin@yandex.ru", "mailto:gkislin@yandex.ru")));
        contacts.put(ContactType.LINKEDIN, new Contact(new Link("Профиль LinkedIn", "https://www.linkedin.com/in/gkislin")));
        contacts.put(ContactType.GITHUB, new Contact(new Link("Профиль GitHub", "https://github.com/gkislin")));
        contacts.put(ContactType.STACKOVERFLOW, new Contact(new Link("Профиль Stackoverflow", "https://stackoverflow.com/users/548473")));
        contacts.put(ContactType.HOMEPAGE, new Contact(new Link("Домашняя страница", "http://gkislin.ru/")));

        sections.put(SectionType.OBJECTIVE, new TextSection("Позиция",
                "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        sections.put(SectionType.PERSONAL, new TextSection("Личные качества",
                "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        sections.put(SectionType.ACHIEVEMENT, new ListSection(achievements));
        sections.put(SectionType.QUALIFICATIONS, new ListSection(qualifications));
        sections.put(SectionType.EXPERIENCE, new ExperienceSection(jobs));
        sections.put(SectionType.EDUCATION, new ExperienceSection(education));

        Resume resume = new Resume(name);
        resume.setContacts(contacts);
        resume.setSections(sections);

        LOG.info(resume.toString());
        LOG.info(resume.getContacts().toString());
        LOG.info(resume.getSections().toString());
    }
}
