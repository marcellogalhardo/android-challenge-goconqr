# Events CRUD

You can download the APK [here][apk] or see the images [here][images].

## Description

Create an Android application that displays information received over the network.
Write an Android application in Java that can:

- GET a list of events.
- POST a new event.
- DELETE an existing event.

## Implementation Details

1. It use the concept of Package by Feature / Domain.
2. It use MVP pattern for presentation and to make tests easier.
3. It use Dagger for Dependency Injection.
4. It use Repository Pattern with OkHttp, Retrofit and Json for data loading.
5. It use RxJava with Retrolambda.
6. It use ButterKnife for view bind.
7. It has some animations: Transition between activities; Ripple effect in touch; Fade out when delete; Up from bottom when scroll.
8. It use SVG.
9. It has some unit testing with JUnit and Mockito (see test folder).
10. It has some Ui tests with Espresso (see androidTest folder).
11. It use CustomView implementation with parameters by XML (see DatePickerView).

**Important:** Unit and Ui tests **must run with Mock flavor**.

## UX / UI

![Events][design-events]
![Events][design-error]
![Events][design-add-event]
![Events][design-loading]

[apk]: https://github.com/marcellogalhardo/events/releases/download/1.0.0/application.apk
[images]: https://github.com/marcellogalhardo/events/tree/master/images
[design-events]: https://github.com/marcellogalhardo/events/blob/1.0.0/images/events.png
[design-add-event]: https://github.com/marcellogalhardo/events/blob/1.0.0/images/add_event.png
[design-loading]: https://github.com/marcellogalhardo/events/blob/master/images/loading.png
[design-error]: https://github.com/marcellogalhardo/events/blob/1.0.0/images/error.png
