# Together Button - Android
## How to use

#### Getting library:

- Add it in your root *build.gradle* at the end of repositories:

```css
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

- Add the dependency

```css
	dependencies {
	        implementation 'com.github.together-project:together-button-android:v0.1.3'
	}
```



#### Usage example:

It's pretty easy ;)

You just need to pass an activity context to *TogetherButton* class and call *show* function.

- **Kotlin**

  ```kotlin
  // Assuming you're inside an activity lifecycle.
  val togetherButton = TogetherButton(this)
  togetherButton.show()
  ```

- **Java**

  ```java
  // Assuming you're inside an activity lifecycle.
  TogetherButton togetherButton = new TogetherButton(this)
  togetherButton.show()
  ```

  