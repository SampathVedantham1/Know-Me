package com.example.personalprofile.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.personalprofile.R
import com.example.personalprofile.data.Education
import com.example.personalprofile.data.Experience
import com.example.personalprofile.data.NavigationDrawerItem
import com.example.personalprofile.data.Project
import com.example.personalprofile.ui.theme.LightGray
import com.example.personalprofile.ui.theme.PastalBlue
import com.example.personalprofile.ui.theme.PastalCream
import com.example.personalprofile.ui.theme.PastalGreen
import com.example.personalprofile.ui.theme.PastalLavendar
import com.example.personalprofile.ui.theme.PastalOrange
import com.example.personalprofile.ui.theme.PastalYellow
import com.example.personalprofile.ui.theme.PersonalProfileTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scopeState = rememberCoroutineScope()
    val navItems = listOf(
        NavigationDrawerItem(
            text = "9666150281",
            icon = R.drawable.call_navigation_item
        ),
        NavigationDrawerItem(
            text = "sampathvedantham@gmail.com",
            icon = R.drawable.mail_navigation_icon
        )
    )
    ModalNavigationDrawer(drawerContent = {
        ModalDrawerSheet(modifier = Modifier.background(LightGray)) {
            NavigationDrawerView(navItems)
        }
    }, drawerState = drawerState) {
        Scaffold(modifier = modifier, topBar = {
            CenterAlignedTopAppBar(title = {
                Column {
                    Text(
                        text = stringResource(id = R.string.full_name),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(horizontalArrangement = Arrangement.Center) {
                        Text(
                            text = stringResource(id = R.string.software_engineer),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.padding(16.dp))
                        Text(
                            text = stringResource(id = R.string.experience),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = stringResource(id = R.string.experience_duration),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }, colors = TopAppBarDefaults.topAppBarColors(
                Color.Transparent
            ), navigationIcon = {
                IconButton(onClick = {
                    scopeState.launch { drawerState.open() }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "More menu"
                    )
                }
            })
        }) {
            HomeScreenSections(
                Modifier
                    .padding(it)
                    .testTag(stringResource(id = R.string.home_sceen_section)))
        }
    }

}

@Composable
fun NavigationDrawerView(navItems: List<NavigationDrawerItem>) {
    Column {
        NavigationDrawerHeader()
        ResumeSection()
        navItems.forEach { navItem ->
            NavigationDrawerItem(
                logo = navItem.icon,
                text = navItem.text
            )
        }
    }
}

@Composable
fun HomeScreenSections(modifier: Modifier = Modifier) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        listOfContents.forEach {
            when (stringResource(it)) {
                "About me" -> {
                    SectionItem(logo = R.drawable.about_me, title = stringResource(it)) {
                        AboutMe()
                    }
                }

                "Experience" -> {
                    SectionItem(logo = R.drawable.work_experience, title = stringResource(it)) {
                        Surface(
                            shape = MaterialTheme.shapes.large, modifier = Modifier.padding(10.dp)
                        ) {
                            ExperienceItemView()
                        }
                    }
                }

                "Key Skills" -> {
                    SectionItem(logo = R.drawable.skills, title = stringResource(it)) {
                        Surface(
                            shape = MaterialTheme.shapes.large, modifier = Modifier.padding(10.dp)
                        ) {
                            Column(modifier = Modifier.background(color = PastalLavendar)) {
                                keySkills.forEach { skill ->
                                    CommonTextView(
                                        text = skill, modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 5.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                "Education" -> {
                    SectionItem(logo = R.drawable.education, title = stringResource(it)) {
                        Surface(
                            shape = MaterialTheme.shapes.large, modifier = Modifier.padding(10.dp)
                        ) { EducationItemView() }
                    }
                }

                "Projects" -> {
                    SectionItem(logo = R.drawable.projects, title = stringResource(it)) {
                        Surface(
                            shape = MaterialTheme.shapes.large, modifier = Modifier.padding(10.dp)
                        ) { ProjectItemView() }
                    }
                }

                "Links" -> {
                    SectionItem(logo = R.drawable.link, title = stringResource(it)) {
                        Surface(
                            shape = MaterialTheme.shapes.large, modifier = Modifier.padding(10.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .background(color = PastalBlue)
                                    .fillMaxWidth()
                                    .padding(5.dp)
                            ) {
                                ClickableTextView(
                                    text = stringResource(id = R.string.linkedIn),
                                    url = stringResource(id = R.string.linkedIn_link)
                                )
                                ClickableTextView(
                                    text = "Git",
                                    url = "https://github.com/SampathVedantham1"
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
    }
}


@Composable
fun AboutMe() {
    Row(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .fillMaxWidth()
            .fillMaxHeight(), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.sri_krishna),
            contentDescription = "My Image",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Surface(
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = stringResource(id = R.string.about_me_description),
                modifier = Modifier
                    .background(color = PastalYellow)
                    .padding(5.dp)
            )
        }
    }
}

@Composable
fun ProjectItemView() {
    Column {
        projects.forEach { project ->
            SectionItem(
                title = project.projectName,
                logo = project.projectLogo,
                modifier = Modifier.background(color = PastalCream)
            ) {
                projectDataItems.forEach { item ->

                    ContentSectionItem(title = item) {
                        when (item) {
                            "Project name" -> {
                                CommonTextView(text = project.projectName)
                            }

                            "Details" -> {
                                CommonTextView(text = project.projectDetails)
                            }

                            "Tec stack" -> {
                                Column {
                                    project.projectStack.forEach {
                                        CommonTextView(text = it)
                                    }
                                }
                            }

                            "Contributors" -> {
                                CommonTextView(text = project.contributors)
                            }

                            "References" -> {
                                ClickableTextView(
                                    text = stringResource(id = R.string.click_me),
                                    url = project.reference,
                                    modifier = Modifier.testTag("git${projects.indexOf(project)}")
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }
}

@Composable
fun ClickableTextView(text: String, url: String, modifier: Modifier = Modifier) {
    val openLinkLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
    Text(
        modifier = modifier.clickable {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            )
            openLinkLauncher.launch(intent)
        },
        text = buildAnnotatedString { append(text) },
        color = Color.Blue
    )
}

@Composable
fun EducationItemView() {
    Column(
        modifier = Modifier
            .background(color = PastalGreen)
            .fillMaxWidth()
    ) {
        educationDataItems.forEach {
            ContentSectionItem(title = it) {
                when (it) {
                    "Institute name" -> {
                        CommonTextView(text = education.institutionName)
                    }

                    "Degree" -> {
                        CommonTextView(text = education.degree)
                    }

                    "Branch" -> {
                        CommonTextView(text = education.branch)
                    }

                    "Marks %" -> {
                        CommonTextView(text = education.marksPercentage.toString())
                    }

                    "Duration" -> {
                        CommonTextView(text = education.duration)
                    }
                }
            }
        }
    }
}

@Composable
fun ExperienceItemView() {
    listOfExperience.forEach { experienceItem ->
        SectionItem(
            logo = experienceItem.companyLogo,
            title = experienceItem.companyName, modifier = Modifier
                .background(color = PastalOrange)
                .padding(vertical = 5.dp)
        ) {
            experienceDataItems.forEach { item ->
                ContentSectionItem(title = item) {
                    when (item) {
                        "Company name" -> {
                            CommonTextView(text = experienceItem.companyName)
                        }

                        "Project name" -> {
                            CommonTextView(text = experienceItem.projectName)
                        }

                        "Tec stack" -> {
                            Column {
                                experienceItem.tecStack.forEach {
                                    CommonTextView(text = it)
                                }
                            }
                        }

                        "Job role" -> {
                            CommonTextView(text = experienceItem.jobRole)
                        }
                        
                        "Duration" -> {
                            CommonTextView(text = experienceItem.duration)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SectionItem(
    logo: Int,
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier) {
        CommonTitle(logo = logo, title = title, modifier = modifier)
        content()
    }
}

@Composable
fun ResumeSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.scan_qr_code),
            modifier = Modifier.padding(vertical = 10.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.my_pdf),
            contentDescription = stringResource(id = R.string.qr_code_content_description),
            modifier = Modifier
                .size(200.dp),
            contentScale = ContentScale.Fit
        )
        Text(
            text = stringResource(id = R.string.or),
            modifier = Modifier.padding(vertical = 10.dp)
        )
        ClickableTextView(
            text = stringResource(id = R.string.click_to_view_resume),
            url = stringResource(id = R.string.resume_link),
            modifier = Modifier
                .padding(5.dp)
                .background(PastalYellow)
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun NavigationDrawerItem(logo: Int, modifier: Modifier = Modifier, text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = logo),
            contentDescription = "navigation image",
            modifier = modifier
                .size(40.dp)
                .padding(10.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(horizontal = 10.dp)
        )
    }
}

@Composable
fun NavigationDrawerHeader() {
    Column(
        modifier = Modifier
            .height(200.dp)
            .background(color = LightGray)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.sri_krishna),
            contentDescription = "Personal image",
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun CommonTitle(
    logo: Int,
    title: String,
    modifier: Modifier,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = logo),
            contentDescription = "image",
            modifier = modifier
                .size(40.dp)
                .padding(10.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun ContentSectionItem(title: String, content: @Composable () -> Unit) {
    Row {
        Text(text = "${title}: ", modifier = Modifier.padding(horizontal = 8.dp))
        content()
    }
}

@Composable
fun CommonTextView(text: String, modifier: Modifier = Modifier) {
    Text(text = text, modifier = modifier)
}

@Composable
fun SetBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color = color)
    }
}

private val experienceDataItems = listOf("Company name", "Project name", "Tec stack", "Job role","Duration")
private val educationDataItems = listOf("Institute name", "Degree", "Branch", "Marks %", "Duration")
private val projectDataItems =
    listOf("Project name", "Tec stack", "Details", "Contributors", "References")

private val rslTecStack = listOf(
    "Clean MVVM architecture",
    "Kotlin",
    "Java",
    "Coroutine",
    "Dagger and Hilt"
)
private val rsl = Experience(
    companyLogo = R.drawable.rsl_logo,
    companyName = "Raja Software Labs",
    projectName = "Google Home Application",
    jobRole = "Provided dedicated support and timely issue resolution to clients following successful app launch. " +
            "Worked on enhancing the application's user interface to align with tablet specifications and integrating features " +
            "tailored for tablet devices. Worked on priority bugs, customer issues and implementing new feature. " +
            "As a software engineer, I both implemented changes and authored unit test cases to assess them and have also worked on accessibility issues",
    tecStack = rslTecStack,
    duration = "May2022 - Oct2023"
)
private val listOfExperience = listOf(rsl)

private val keySkills = listOf(
    "Java",
    "Kotlin",
    "Android application development",
    "Android studio",
    "Git",
    "Coroutines",
    "Unit testing",
    "XML",
    "Jetpack Compose",
    "MVVM",
    "Debugging"
)

private val inklyProject = Project(
    projectLogo = R.drawable.inkly_logo,
    projectName = "Inkly",
    projectStack = listOf(
        "Dagger and Hilt",
        "Jetpack Compose",
        "Kotlin",
        "Room",
        "Coroutines"
    ),
    projectDetails = "Inkly stands out as a user-friendly note-taking application, offering a seamless note-taking experience, advanced sorting features, and the ability to customize the color of your notes.",
    contributors = "Sampath",
    reference = "https://github.com/SampathVedantham1/NotePad/tree/first-version"
)

private val gitHubRepoProject = Project(
    projectLogo = R.drawable.github_project,
    projectName = "GitHub-Explorer",
    projectStack = listOf(
        "Kotlin",
        "Room",
        "Retrofit",
        "Coroutines",
        "MVVM"
    ),
    projectDetails = "In this application we acheived the following. Fetching the data from the GitHub API using Retrofit, Caching the fetched data using Room database , Searching the repositories using remote API, Integrating the FCM for push notifications(shown in the explanation video), Adding support to signout.",
    contributors = "Sampath",
    reference = "https://github.com/SampathVedantham1/GitHub-Explorer/tree/main"
)
private val projects = listOf(inklyProject, gitHubRepoProject)

private val education = Education(
    institutionName = "Vignans institute of information technology",
    degree = "Bachelor of Technology",
    branch = "Electrical and Electronics",
    duration = "2014 – 2018",
    marksPercentage = 70.13
)

private val listOfContents = listOf(
    R.string.about_me,
    R.string.experience_plain,
    R.string.key_skills,
    R.string.education,
    R.string.projects,
    R.string.links
)

@Preview(showBackground = true)
@Composable
fun NavigationDrawerPreview() {
    PersonalProfileTheme {
        NavigationDrawerView(
            navItems = listOf(
                NavigationDrawerItem(
                    text = "9666150281",
                    icon = R.drawable.call_navigation_item
                ),
                NavigationDrawerItem(
                    text = "sampathvedantham@gmail.com",
                    icon = R.drawable.mail_navigation_icon
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AboutMePreview() {
    PersonalProfileTheme {
        AboutMe()
    }
}

@Preview(showBackground = true)
@Composable
fun ProjectItemPreview() {
    PersonalProfileTheme {
        ProjectItemView()
    }
}

@Preview(showBackground = true)
@Composable
fun EducationItemViewPreview() {
    PersonalProfileTheme {
        EducationItemView()
    }
}

@Preview(showBackground = true)
@Composable
fun ExperienceItemViewPreview() {
    PersonalProfileTheme {
        ExperienceItemView()
    }
}

@Preview(showBackground = true)
@Composable
fun ResumeSectionPreview() {
    PersonalProfileTheme {
        ResumeSection()
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationDrawerItemPreview() {
    PersonalProfileTheme {
        NavigationDrawerItem(
            logo = R.drawable.call_navigation_item,
            modifier = Modifier,
            text = "9666150281"
        )
    }
}

@Preview
@Composable
fun NavigationDrawerHeaderPreview() {
    PersonalProfileTheme {
        NavigationDrawerHeader()
    }
}

@Preview(showBackground = true)
@Composable
fun CommonTilePreview() {
    PersonalProfileTheme {
        CommonTitle(
            logo = R.drawable.link,
            title = "Link",
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContentSectionItemPreview() {
    PersonalProfileTheme {
        ContentSectionItem(title = "Content section Item preview Heading") {
            CommonTextViewPreview()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CommonTextViewPreview() {
    PersonalProfileTheme {
        CommonTextView("Common text view preview")
    }
}

@Preview(showBackground = true)
@Composable
fun SectionItemPreview() {
    PersonalProfileTheme {
        SectionItem(title = stringResource(id = R.string.key_skills), logo = R.drawable.skills) {
            CommonTextView(
                "Common text view preview", modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
                    .background(color = PastalBlue)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PersonalProfileTheme {
        HomeScreen()
    }
}