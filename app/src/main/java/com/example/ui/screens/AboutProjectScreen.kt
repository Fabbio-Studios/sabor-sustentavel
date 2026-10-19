package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.NeobrutalistButton
import com.example.ui.components.NeobrutalistCard
import com.example.ui.theme.EcoDeepGreen
import com.example.ui.theme.EcoInk
import com.example.ui.theme.EcoLime
import com.example.ui.theme.EcoParchment
import com.example.ui.theme.EcoSurface
import com.example.ui.theme.SpaceGrotesk

@Composable
fun AboutProjectScreen(
  onBack: () -> Unit
) {
  BackHandler { onBack() }

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .background(EcoParchment)
      .testTag("about_screen"),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // 1. Navigation Top Bar
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        NeobrutalistButton(
          text = "Voltar",
          icon = Icons.AutoMirrored.Filled.ArrowBack,
          onClick = onBack,
          backgroundColor = EcoSurface,
          textColor = EcoDeepGreen,
          borderWidth = 2.dp,
          shadowOffset = 2.5.dp,
          testTag = "btn_about_back"
        )
      }
    }

    // 2. Title Card (EcoLime Accent)
    item {
      NeobrutalistCard(
        backgroundColor = EcoLime,
        borderColor = EcoDeepGreen,
        shadowColor = EcoDeepGreen,
        borderWidth = 2.dp,
        shadowOffset = 3.5.dp,
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.School,
              contentDescription = null,
              tint = EcoDeepGreen,
              modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "7º Ano • Projeto Escolar",
              fontFamily = SpaceGrotesk,
              fontWeight = FontWeight.Medium,
              fontSize = 12.sp,
              color = EcoDeepGreen
            )
          }

          Spacer(modifier = Modifier.height(6.dp))

          Text(
            text = "Sobre o projeto",
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = EcoDeepGreen
          )

          Spacer(modifier = Modifier.height(4.dp))

          Text(
            text = "Livro de receitas sustentáveis com aproveitamento integral dos alimentos.",
            fontFamily = SpaceGrotesk,
            fontSize = 14.sp,
            color = EcoDeepGreen
          )
        }
      }
    }

    // 3. Project Explanation
    item {
      NeobrutalistCard(
        backgroundColor = EcoSurface,
        borderColor = EcoDeepGreen,
        shadowColor = EcoDeepGreen,
        borderWidth = 2.dp,
        shadowOffset = 3.dp,
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.Eco,
              contentDescription = null,
              tint = EcoDeepGreen,
              modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Objetivo",
              fontFamily = SpaceGrotesk,
              fontWeight = FontWeight.Bold,
              fontSize = 15.sp,
              color = EcoDeepGreen
            )
          }
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = "Este aplicativo reúne receitas testadas pelos alunos para usar partes de alimentos que costumam ir para o lixo, como cascas de banana, talos de couve e brócolis, entrecasca de melancia e folhas de beterraba.\n\n" +
              "O objetivo é mostrar na prática que essas partes têm sabor, contêm nutrientes e diminuem o desperdício na cozinha de casa.",
            fontFamily = SpaceGrotesk,
            fontSize = 14.sp,
            color = EcoInk,
            lineHeight = 20.sp
          )
        }
      }
    }

    // 4. Practical Rules
    item {
      NeobrutalistCard(
        backgroundColor = EcoSurface,
        borderColor = EcoDeepGreen,
        shadowColor = EcoDeepGreen,
        borderWidth = 2.dp,
        shadowOffset = 3.dp,
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.TipsAndUpdates,
              contentDescription = null,
              tint = EcoDeepGreen,
              modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Cuidados na cozinha",
              fontFamily = SpaceGrotesk,
              fontWeight = FontWeight.Bold,
              fontSize = 15.sp,
              color = EcoDeepGreen
            )
          }
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = "1. Higienização: lavar bem cascas e talos em água corrente antes de usar.\n" +
              "2. Armazenamento: talos picados podem ser congelados em potes para usar depois.\n" +
              "3. Compostagem: o que não for consumido (como caroços duros) vai para a horta da escola.",
            fontFamily = SpaceGrotesk,
            fontSize = 13.sp,
            color = EcoInk,
            lineHeight = 20.sp
          )
        }
      }
    }

    // 5. Credits
    item {
      NeobrutalistCard(
        backgroundColor = EcoSurface,
        borderColor = EcoDeepGreen,
        shadowColor = EcoDeepGreen,
        borderWidth = 2.dp,
        shadowOffset = 3.dp,
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.People,
              contentDescription = null,
              tint = EcoDeepGreen,
              modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Equipe",
              fontFamily = SpaceGrotesk,
              fontWeight = FontWeight.Bold,
              fontSize = 15.sp,
              color = EcoDeepGreen
            )
          }
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "Trabalho realizado pelas turmas do 7º Ano A, B e C nas aulas de Ciências e Educação Ambiental.",
            fontFamily = SpaceGrotesk,
            fontSize = 13.sp,
            color = EcoInk.copy(alpha = 0.8f),
            lineHeight = 18.sp
          )
        }
      }
    }

    item {
      Spacer(modifier = Modifier.height(8.dp))
      NeobrutalistButton(
        text = "Voltar ao catálogo",
        icon = Icons.AutoMirrored.Filled.ArrowBack,
        onClick = onBack,
        backgroundColor = EcoDeepGreen,
        textColor = EcoSurface,
        modifier = Modifier.fillMaxWidth()
      )
      Spacer(modifier = Modifier.height(20.dp))
    }
  }
}
