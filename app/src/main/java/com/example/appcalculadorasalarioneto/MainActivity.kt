package com.example.appcalculadorasalarioneto

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.appcalculadorasalarioneto.ui.theme.AppCalculadoraSalarioNetoTheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppCalculadoraSalarioNetoTheme {
                NavegacionWeb(datos = Datos(0,0,.0f,"",0,.0f,0,false))
            }
        }
    }


}

// URL del repositorio de Github es:
// https://github.com/lrv491/AplicacionCalculadoraSalarioNeto.git
@Composable
fun NavegacionWeb(datos: Datos){
    // Controlador de navegacion
    val navController = rememberNavController()

    // Contenedor de rutas
    NavHost(navController = navController,startDestination = "pantalla1"){
        composable("pantalla1"){
            Pantalla1(datos, navController)
        }
        composable("pantalla2"){
            Pantalla2(datos, navController)
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pantalla1(datos: Datos, navController: NavController) {
    // Estructura de la primera pantalla
    var edadTexto by rememberSaveable { mutableStateOf("") }
    var grupoProfesional by rememberSaveable { mutableFloatStateOf(1.0f) }
    var gradoDiscapacidad by rememberSaveable { mutableFloatStateOf(.0f)}
    var estadoCivil by rememberSaveable { mutableStateOf("") }
    var numHijos by rememberSaveable { mutableStateOf("") }
    var salarioBrutoAnual by rememberSaveable { mutableStateOf("") }
    var numPagas by rememberSaveable { mutableStateOf("") }
    var mensajeError by rememberSaveable { mutableStateOf<String?>(null) }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.tertiary,
                ),
                title = {
                    Text(
                        text = "Calculadora de Salario Neto",
                        fontSize = 30.sp
                    )
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            shape = RectangleShape,
            color = Color(0xFFDDD8FF)
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(Modifier.height(20.dp))
                // EDAD
                OutlinedTextField(
                    value = edadTexto,
                    onValueChange = {
                        edadTexto = it.trim()
                    },
                    label ={
                        Text("Edad",
                            color =Color(0xFF4A1B7F) )
                    },
                    colors = TextFieldDefaults.colors(
                        // Color de fondo del contenedor cuando esta activo
                        focusedContainerColor = Color(0xA1B1A9D9),
                        // Color de fondo del contenedor cuando no activo
                        unfocusedContainerColor = Color(0xFFE8DEF8),
                        // Color del cursor
                        cursorColor = Color.Black,
                        // Color del borde del contenedor cuando no esta activo
                        unfocusedIndicatorColor = Color(0xFF4A1B7F),
                        // Color del borde del contenedor cuando esta activo
                        focusedIndicatorColor = Color(0xFF4A1B7F)
                    )
                )

                Spacer(Modifier.height(30.dp))

                // Grupo Profesional
                Text(
                    text = "Grupo Profesional ${grupoProfesional.toInt()}",
                    color =Color(0xFF4A1B7F)
                )
                Slider(
                    value = grupoProfesional,
                    onValueChange = {
                        grupoProfesional=it
                    },
                    valueRange = 1f..11f,
                    steps = 9,
                    modifier = Modifier.width(280.dp)
                )
                Spacer(Modifier.height(20.dp))

                // Estado civil
                OutlinedTextField(
                    value = estadoCivil,
                    onValueChange = {
                        estadoCivil = it.trim()
                    },
                    label ={
                        Text("Estado Civil",
                            color =Color(0xFF4A1B7F))
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xA1B1A9D9),
                        unfocusedContainerColor = Color(0xFFE8DEF8),
                        cursorColor = Color.Black,
                        unfocusedIndicatorColor = Color(0xFF4A1B7F),
                        focusedIndicatorColor = Color(0xFF4A1B7F)
                    )
                )
                Spacer(Modifier.height(20.dp))

                // Numero de hijos
                OutlinedTextField(
                    value = numHijos,
                    onValueChange = {
                        numHijos = it.trim()
                    },
                    label ={
                        Text("Hijos",
                            color =Color(0xFF4A1B7F))
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xA1B1A9D9),
                        unfocusedContainerColor = Color(0xFFE8DEF8),
                        cursorColor = Color.Black,
                        unfocusedIndicatorColor = Color(0xFF4A1B7F),
                        focusedIndicatorColor = Color(0xFF4A1B7F)
                    )
                )
                Spacer(Modifier.height(30.dp))

                // Grado de discapacidad
                Text(
                    text = "Grado de Discapacidad",
                    color =Color(0xFF4A1B7F)
                )
                Text(
                    text = "${gradoDiscapacidad.toInt()}%",
                    color =Color(0xFF4A1B7F)
                )
                Slider(
                    value = gradoDiscapacidad,
                    onValueChange = {
                        gradoDiscapacidad=it
                    },
                    valueRange = 0f..100f,
                    steps = 19,
                    modifier = Modifier.width(280.dp)
                )
                Spacer(Modifier.height(20.dp))

                // Salario Bruto Anual
                OutlinedTextField(
                    value = salarioBrutoAnual,
                    onValueChange = {
                        salarioBrutoAnual = it.trim()
                    },
                    label ={
                        Text("Salario Bruto Anual",
                            color =Color(0xFF4A1B7F))
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xA1B1A9D9),
                        unfocusedContainerColor = Color(0xFFE8DEF8),
                        cursorColor = Color.Black,
                        unfocusedIndicatorColor = Color(0xFF4A1B7F),
                        focusedIndicatorColor = Color(0xFF4A1B7F)
                    )
                )
                Spacer(Modifier.height(20.dp))

                // Numero de pagas
                OutlinedTextField(
                    value = numPagas,
                    onValueChange = {
                        numPagas = it.trim()
                    },
                    label ={
                        Text("Número de Pagas Anuales",
                            color =Color(0xFF4A1B7F))
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xA1B1A9D9),
                        unfocusedContainerColor = Color(0xFFE8DEF8),
                        cursorColor = Color.Black,
                        unfocusedIndicatorColor = Color(0xFF4A1B7F),
                        focusedIndicatorColor = Color(0xFF4A1B7F)
                    )
                )
                Spacer(Modifier.height(20.dp))

                // Boton de cambio de pantalla
                Button(
                    onClick = {
                        if(edadTexto == "" || numHijos == "" || numPagas == "" || estadoCivil == "" || salarioBrutoAnual == ""){
                            mensajeError = "Debes rellenar todos los campos"
                        }else{
                            if (edadTexto.isDigitsOnly() && numHijos.isDigitsOnly() && numPagas.isDigitsOnly() && salarioBrutoAnual.isDigitsOnly()){
                                datos.edad = edadTexto.trim().toInt()
                                datos.edad = edadTexto.trim().toInt()
                                datos.hijos = numHijos.trim().toInt()
                                datos.salario = salarioBrutoAnual.trim().toFloat()
                                datos.pagas = numPagas.trim().toInt()
                            }else{
                                datos.err = true
                            }
                            datos.grupo = grupoProfesional.toInt()
                            datos.discapacidad = gradoDiscapacidad
                            datos.estadoCivil = estadoCivil.trim()

                            navController.navigate("pantalla2")
                        }
                    }
                ) {
                    Text("Calcular")
                }
                Spacer(Modifier.height(20.dp))

                if (mensajeError!=null){
                    Text(mensajeError!!,
                        color = Color.Red)
                }

            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pantalla2(datos: Datos,navController: NavController) {
    // Estructura de la segunda pantalla
    var salarioBruto by rememberSaveable { mutableFloatStateOf(.0f) }
    var salarioNeto by rememberSaveable { mutableFloatStateOf(.0f) }
    var retencion  by rememberSaveable { mutableFloatStateOf(.0f) }
    var deducciones by rememberSaveable { mutableStateOf("Gastos por desplazamiento: 600€") }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.tertiary,
                ),
                title = {
                    Text(
                        text = "Calculadora de Salario Neto",
                        fontSize = 30.sp
                    )
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            shape = RectangleShape,
            color = Color(0xFFDDD8FF)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(20.dp))

                Text(
                    text = "Resultado",
                    style = TextStyle(
                        color =Color(0xFF4A1B7F),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(Modifier.height(20.dp))

                if (!datos.err){
                    // Calculo de las operaciones
                    // Todas ellas estan inventadas, son calculos ficticios pero que cambian
                    // dependiendo los valores

                    salarioBruto = datos.salario/datos.pagas
                    if(datos.estadoCivil.startsWith("solter")) {
                        if(datos.hijos<=0){
                            retencion = when(datos.salario){
                                15000f -> 2f
                                in 16000f .. 25000f -> 8f
                                in 26000f .. 35000f -> 14f
                                in 36000f .. 45000f -> 19f
                                else -> 22f
                            }
                        }else{
                            retencion = 10f
                        }
                    }else{
                        if(datos.hijos > 0){
                            retencion = when(datos.salario){
                                15000f -> 0f
                                in 16000f .. 25000f -> 7f
                                in 26000f .. 35000f -> 12f
                                in 36000f .. 45000f -> 14f
                                else -> 17f
                            }

                        }else{
                            retencion = 14f
                        }
                    }
                    salarioNeto = (datos.salario*(1-(retencion/100)))

                    // Implementacion en la app
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(30.dp,15.dp),
                    ){
                        Text(
                            text = "Salario Bruto: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Text(
                            text = String.format("%.2f€",datos.salario),
                            fontSize = 20.sp
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(30.dp,15.dp)

                    ) {
                        Text(
                            text = "Salario Neto: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Text(
                            text = String.format("%.2f€",salarioNeto),
                            fontSize = 20.sp
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(30.dp,15.dp),
                    ){
                        Text(
                            text = "Salario Bruto Mensual: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Text(
                            text = String.format("%.2f€",salarioBruto),
                            fontSize = 20.sp
                        )
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth()
                            .padding(30.dp,15.dp)
                    ){
                        Text(
                            text = "Retención del IRPF: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Text(
                            text = "$retencion%",
                            fontSize = 20.sp
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(30.dp,15.dp)
                    ) {
                        Text(
                            "Posibles Deducciones: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Text(
                            text = deducciones,
                            fontSize = 20.sp
                        )
                    }
                }else{
                    Spacer(Modifier.height(20.dp))
                    Text(
                        "ERROR",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                    Text(
                        "Algunos datos no se pueden validar",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(Modifier.height(20.dp))


                    // Final del else
                }

                Spacer(Modifier.height(20.dp))
                // Boton de cambio de pantalla
                Button(
                    onClick = {
                        datos.err = false
                        navController.navigate("pantalla1")
                    }
                ) {
                    Text("Volver a Inicio")
                }

            }

        }
    }
}

