# Breathosphere-Sdk
Breath pulse container Jetpack compose SDk

## How to integrate?
 Add maven jitpack to project level dependancy in build.gradle
 
 	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
 Add Breathosphere Sdk dependancy to project in app level build.gradle
 
 	dependencies {
          //...OTHER DEPENDANCIES
	        implementation 'com.github.harishpadmanabh-HP:Brerathosphere-Sdk:1.2'
	}
  
 Alternatively the `breathpulse-composable` module can downloaded and imported in your existing project.

## Configure breath pulse view constraints.

The configurations are passed through [BreathConfig class](https://github.com/harishpadmanabh-HP/Breathosphere-Sdk/blob/master/breathpulse-composable/src/main/java/com/hpcoding/breathpulse_composable/breathPulse/BreathConfig.kt). Take a look here for its definition.


## Usage


[BreathPulseContainer()](https://github.com/harishpadmanabh-HP/Breathosphere-Sdk/blob/master/breathpulse-composable/src/main/java/com/hpcoding/breathpulse_composable/breathPulse/BreathPulseContainer.kt) provides complete screen elements including total duration,breath view,single inhale/exhale timer,hold timer/icon,start button,voice overs,background music.
with custimizations options. For taking control over each single composable, take a look on how its used in the above link and customize according to your own needs.

The state of [BreathView](https://github.com/harishpadmanabh-HP/Breathosphere-Sdk/blob/master/breathpulse-composable/src/main/java/com/hpcoding/breathpulse_composable/breathPulse/BreathPulseView.kt) is handled by [BreathViewState](https://github.com/harishpadmanabh-HP/Breathosphere-Sdk/blob/master/breathpulse-composable/src/main/java/com/hpcoding/breathpulse_composable/breathPulse/BreathViewState.kt)

    val state: BreathViewState = rememberBreathViewState().also {
        it.setConfig(
            relaxTime = 5,
            cycles = breathConfig.cycle,
            inhale = breathConfig.inhaleTime,
            exhale = breathConfig.exhaleTime,
            inhaleHold = breathConfig.inhaleHoldTime,
            exhaleHold = breathConfig.exhaleHoldTime,
            showHoldTime = breathConfig.showHoldTimer
        )
    }


For simple usage just call 

`BreathPulseContainer(breathConfig=yourBreathConfigObject)` 

composable function with your breathConfig object passed
Usage Example

            BreathPulseContainer(
                surfaceColor = viewModel.breathConfig.surfaceColor,
                pulseColor = viewModel.breathConfig.pulseColor,
                breathConfig = viewModel.breathConfig,
                holdIconComposable = {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_pause_24),
                        contentDescription = ""
                    )
                }
            )
            
            
 Sample usages are done in MainActivity.kt.
 
 

 
 # Happy Coding !
 

