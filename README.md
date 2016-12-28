# Events CRUD

You can download the APK [here][apk].
It was developed to serve as an example of RxJava, MVP and Clean Architecture.

## Description

Create an Android application that displays information received over the network.
Write an Android application in Java that can:

- GET a list of events.
- POST a new event.
- DELETE an existing event.
- The application should display validation errors when appropriate.

## Implementation Details

1. It's use the concept of Package by Feature / Domain.
2. It's use MVP pattern for presentation and to make tests easier.
3. It's use Dagger for Dependency Injection.
4. It's use Repository Pattern with OkHttp, Retrofit and Json for data loading.
5. It's use RxJava with Retrolambda.
6. It's use ButterKnife for view bind.
7. It was some animations:
7.1. Transition between activities;
7.2. Ripple effect in touch;
7.3. Fade out when delete;
7.4. Up from bottom when scroll.
8. It's use SVG.
9. It was some unit testing with JUnit and Mockito (see test folder).
10. It was some Ui tests with Espresso (see androidTest folder).

Obs: Unit and Ui tests *must run with Mock flavor*.

## UX / UI

![Events][design-events]
![Events][design-add-event]
![Events][design-loading]
![Events][design-error]

[apk]: https://github.com/marcellogalhardo/events/releases/download/1.0.0/application.apk
[design-events]: https://github.com/marcellogalhardo/events/blob/1.0.0/images/events.png
[design-add-event]: https://github.com/marcellogalhardo/events/blob/1.0.0/images/add_event.png
[design-loading]: https://github.com/marcellogalhardo/events/blob/1.0.0/images/events.png
[design-error]: https://github.com/marcellogalhardo/events/blob/1.0.0/images/error.png
