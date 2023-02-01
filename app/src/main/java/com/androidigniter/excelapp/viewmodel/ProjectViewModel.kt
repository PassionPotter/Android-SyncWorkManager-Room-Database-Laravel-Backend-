package com.androidigniter.excelapp.viewmodel


import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.work.*
import com.androidigniter.excelapp.model.*
import com.androidigniter.excelapp.repository.*
import com.androidigniter.excelapp.workmanager.SyncWorkManager

class ProjectViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    private val companyRepository: CompanyRepository
    val wtgTypeRepository:WTGTypeRepository
    private val subContractorRepository:SubContractorRepository

    val allCompanies: List<CompanyResponse>
    val allWTGType:List<WTGTypeResponse>
    val allsubContractor:List<SubContractorResponse>

    val projectRepository: ProjectRepository
    val craneRepository:CraneRepository
    val wtgRepository:WTGRepository
    val compoentRepository:ComponentRepository
    val phaseRepository:PhaseRepository
    val ots1Repository:Ots1Repository

    private val personelRepository:PersonelRepository
    private val craneRecolocationRepository:CraneRecolocationRepository
    private val resourceActivationRepository:ResourceActivationRepository
    private val taskRepository:TaskRepository
    private val dailyStatusRepository:DailyStatusRepository
    private val craneTaskRepository:CraneTaskRepository
    private val craneDailyStatusRepository:CraneDailyStatusRepository
    private val inefficienciesRepository:InefficienciesRepository
    private val craneInefficienciesRepository:CraneInefficienciesRepository
    private val trazabilityRepository:TrazabilityRepository
    private val craneTrazabilityRepository:CraneTrazabilityRepository



    private val mWorkManager: WorkManager

    val allCranes:List<CraneResponse>
    val allWTGs:List<WTGResponse>
    val allTasks:List<TaskResponse>
    val allProjects:List<ProjectResponse>
    val allCraneTasks:List<CraneTaskResponse>
    val allInefficiencies:List<InefficienciesResponse>
    val allCraneInefficiencies:List<CraneInefficienciesResponse>
    val allTrazability:List<TrazabilityResponse>
    val allCraneTrazability:List<CraneTrazabilityResponse>
    val allComponents:List<ComponentResponse>
    val allPhase:List<PhaseResponse>
    val allOts1:List<OTS1Response>
    init {
        companyRepository = CompanyRepository(application)
        wtgTypeRepository = WTGTypeRepository(application)
        subContractorRepository = SubContractorRepository(application)
        craneInefficienciesRepository = CraneInefficienciesRepository(application)
        allCompanies = companyRepository.allCompanies
        allWTGType = wtgTypeRepository.allWTGTypes
        allsubContractor = subContractorRepository.allSubContractors
        allCraneInefficiencies = craneInefficienciesRepository.allInefficiencies

        projectRepository = ProjectRepository(application)
        craneRepository = CraneRepository(application)
        wtgRepository = WTGRepository(application)
        personelRepository = PersonelRepository(application)
        craneRecolocationRepository = CraneRecolocationRepository(application)
        resourceActivationRepository = ResourceActivationRepository(application)
        taskRepository = TaskRepository(application)
        dailyStatusRepository = DailyStatusRepository(application)
        craneTaskRepository = CraneTaskRepository(application)
        craneDailyStatusRepository = CraneDailyStatusRepository(application)
        inefficienciesRepository = InefficienciesRepository(application)
        trazabilityRepository = TrazabilityRepository(application)
        craneTrazabilityRepository = CraneTrazabilityRepository(application)
        phaseRepository = PhaseRepository(application)
        ots1Repository = Ots1Repository(application)
        compoentRepository = ComponentRepository(application)

        allCranes = craneRepository.allCranes
        allWTGs = wtgRepository.allWTGs
        allTasks = taskRepository.allTasks
        allProjects = projectRepository.allProjects
        allCraneTasks = craneTaskRepository.allTasks
        allInefficiencies = inefficienciesRepository.allInefficiencies
        allTrazability = trazabilityRepository.allTrazability
        allCraneTrazability = craneTrazabilityRepository.allTrazability
        allPhase = phaseRepository.allPhase
        allOts1 = ots1Repository.allOts
        allComponents = compoentRepository.allComponents

        mWorkManager = WorkManager.getInstance()
    }

    fun insert(project:ProjectResponse) {
        projectRepository.insert(project)
    }
    fun insertCrane(crane:CraneResponse) {
        craneRepository.insert(crane)
    }
    fun insertWTG(wtg:WTGResponse) {
        wtgRepository.insert(wtg)
    }

    fun insertPersonel(personel:PersonelResponse) {
        personelRepository.insert(personel)
    }
    fun insertCraneRecolocation(craneRecolocation:CraneRecolocationResponse) {
        craneRecolocationRepository.insert(craneRecolocation)
    }
    fun insertResourceActivation(resourceActivation:ResourceActivationResponse) {
        resourceActivationRepository.insert(resourceActivation)
    }
    fun insertTask(task:TaskResponse) {
        taskRepository.insert(task)
    }
    fun insertDailyStatus(dailyStatus:DailyStatusResponse) {
        dailyStatusRepository.insert(dailyStatus);
    }
    fun insertCraneTask(task:CraneTaskResponse) {
        craneTaskRepository.insert(task)
    }
    fun insertCraneDailyStatus(dailyStatus:CraneDailyStatusResponse) {
        craneDailyStatusRepository.insert(dailyStatus);
    }
    fun insertInEfficiencies(inefficiences:InefficienciesResponse) {
        inefficienciesRepository.insert(inefficiences)
    }
    fun insertCraneInEfficiencies(inefficiences:CraneInefficienciesResponse) {
        craneInefficienciesRepository.insert(inefficiences)
    }
    fun insertTrazability(trazability:TrazabilityResponse) {
        trazabilityRepository.insert(trazability)
    }
    fun insertCraneTrazability(trazability:CraneTrazabilityResponse) {
        craneTrazabilityRepository.insert(trazability)
    }
    fun insertOts1(ots1:OTS1Response) {
        ots1Repository.insert(ots1)
    }

    fun startSync(lifecycleOwner:LifecycleOwner) {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresCharging(false)
                .build()

        val noteWorker = OneTimeWorkRequest.Builder(SyncWorkManager::class.java).setConstraints(constraints).build()

        mWorkManager.enqueue(noteWorker)

        mWorkManager.getWorkInfoByIdLiveData(noteWorker.id).observe(lifecycleOwner, Observer{ workInfo:WorkInfo? ->
            if(workInfo != null && workInfo.state.isFinished) {
                Toast.makeText(context, "Synchronization Finished!!!",Toast.LENGTH_LONG).show();
            }
        })

    }
}