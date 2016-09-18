## Example app to show how to migrate SAStruts apps

This is a working example for the following talk at the Seasar conference.

["SAStruts + S2JDBC から Skinny Framework への移行ガイド"](https://event.seasarfoundation.org/sc2016/speaker/kazuhiro-sera/)

[SAStruts](http://sastruts.seasar.org/) is a web framework well-known in Japan several years ago.
[The Seasar Project](http://www.seasar.org/en/) decided to quit maintaining their open source projects on 24 September, 2016.

If you were familiar with SAStruts, I'm appreciated of your help!

## Getting Started

- sa-struts-tutorial: the original tutorial app
- skinny-tutorial: alternative one built with Skinny Framework

### How to run

#### SAStruts

```
cd sa-struts-tutorial
mvn jetty:run
```

Access http://localhost:8081/sa-struts-tutorial/ from your browser.

<img width="822" alt="screen shot 2016-09-18 at 13 42 36" src="https://cloud.githubusercontent.com/assets/19658/18612937/cdcd4582-7da5-11e6-9a24-eba95b0ce6ce.png">

#### Skinny

```
./skinny run
```

- sa-struts-tutorial: http://localhost:8080/sa-struts-tutorial/ (partially implemented)
- scaffolding from `sql/create.sql`: /addresses, /departments and /employees

<img width="1184" alt="screen shot 2016-09-18 at 13 45 21" src="https://cloud.githubusercontent.com/assets/19658/18612945/2e243a6c-7da6-11e6-9297-184abcbfbe3d.png">

## License

```
Apache License, Version 2.0
http://www.apache.org/licenses/LICENSE-2.0.html
```
