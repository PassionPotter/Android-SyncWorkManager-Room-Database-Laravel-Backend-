package com.androidigniter.excelapp.workmanager

import android.app.Activity
import android.app.Application
import android.app.Person
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.lifecycle.Observer
import androidx.work.Data
import androidx.work.ListenableWorker

import androidx.work.Worker
import androidx.work.WorkerParameters
import com.androidigniter.excelapp.LoginActivity
import com.androidigniter.excelapp.SessionHandler
import com.androidigniter.excelapp.data.DataActivity
import com.androidigniter.excelapp.model.CompanyResponse
import com.androidigniter.excelapp.repository.*
import com.androidigniter.excelapp.retrofit.RemoteCompanyService
import com.androidigniter.excelapp.retrofit.RemoteUserEndPoint
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SyncWorkManager(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    internal var companyRepository: CompanyRepository
    internal var wtgTypeRepository:WTGTypeRepository
    internal var subContractorRepository:SubContractorRepository
    internal val componentRepository:ComponentRepository
    internal val phaseRepository:PhaseRepository
    internal val ots1Repository:Ots1Repository

    internal var craneRepository:CraneRepository
    internal var projectRepository:ProjectRepository
    internal var wtgRepository:WTGRepository
    internal var personelRepository:PersonelRepository
    internal var craneRecolocationRepository:CraneRecolocationRepository
    internal var resourceActivationRepository:ResourceActivationRepository
    internal var taskRepository:TaskRepository
    internal var dailyStatusRepository:DailyStatusRepository
    internal var craneTaskRepository:CraneTaskRepository
    internal var craneDailyStatusRepository:CraneDailyStatusRepository
    internal var inefficienciesRepository:InefficienciesRepository
    internal var craneInefficienciesRepository:CraneInefficienciesRepository
    internal val trazabilityRepository:TrazabilityRepository
    internal val craneTrazabilityRepository:CraneTrazabilityRepository
    internal var context:Context
    private val session:SessionHandler
    init {

        companyRepository = CompanyRepository(applicationContext as Application)
        wtgTypeRepository = WTGTypeRepository(applicationContext as Application)
        subContractorRepository = SubContractorRepository(applicationContext as Application)
        craneRepository = CraneRepository(applicationContext as Application)
        projectRepository = ProjectRepository(applicationContext as Application)
        wtgRepository = WTGRepository(applicationContext as Application)
        personelRepository = PersonelRepository(applicationContext as Application)
        craneRecolocationRepository = CraneRecolocationRepository(applicationContext as Application)
        resourceActivationRepository = ResourceActivationRepository(applicationContext as Application)
        taskRepository = TaskRepository(applicationContext as Application)
        dailyStatusRepository = DailyStatusRepository(applicationContext as Application)
        craneTaskRepository = CraneTaskRepository(applicationContext as Application)
        craneDailyStatusRepository = CraneDailyStatusRepository(applicationContext as Application)
        inefficienciesRepository = InefficienciesRepository(applicationContext as Application)
        craneInefficienciesRepository = CraneInefficienciesRepository(applicationContext as Application)
        trazabilityRepository = TrazabilityRepository(applicationContext as Application)
        craneTrazabilityRepository = CraneTrazabilityRepository(applicationContext as Application)
        componentRepository = ComponentRepository(applicationContext as Application)
        phaseRepository = PhaseRepository(applicationContext as Application)
        ots1Repository = Ots1Repository(applicationContext as Application)

        session = SessionHandler(applicationContext as Application)

        this.context = context
    }
    fun checkUserInfo() {
        val headers:HashMap<String, String> = HashMap<String, String>()
        headers.put("Authorization", "Bearer " + session.userDetails.token)
        val service = RemoteCompanyService.client.create(RemoteUserEndPoint::class.java)
        val call = service.checkUser(headers)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.code() == 200) {
                    val responseString:String = response.body().string();
                    if(responseString == "failed") {
                        session.logoutUser()
                        var i:Intent = Intent( context, LoginActivity::class.java)
                        context.startActivity(i)
                    }
                    else {
                        session.changeRole(response.body().toString());
                    }

                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            }
        })
    }

    override fun doWork(): ListenableWorker.Result {

        checkUserInfo()
        companyRepository.syncCompany()
        wtgTypeRepository.syncWTGType()
        subContractorRepository.syncSubContractor()
        phaseRepository.sync()
        componentRepository.sync()

        craneRepository.syncCrane()
        projectRepository.syncCrane()
        wtgRepository.syncWTG()
        personelRepository.syncPersonel()
        craneRecolocationRepository.syncCraneRecolocation()
        resourceActivationRepository.syncResourceActivation()
        taskRepository.syncTask()
        dailyStatusRepository.syncDailyStatus()
        craneDailyStatusRepository.syncDailyStatus()
        craneTaskRepository.syncTask()
        inefficienciesRepository.syncTask()
        craneInefficienciesRepository.syncTask()
        trazabilityRepository.syncTask()
        craneTrazabilityRepository.syncTask()
        ots1Repository.sync()

        craneRepository.downloadCranes()
        projectRepository.downloadProjects()
        wtgRepository.downloadWTGs()
        taskRepository.downloadTask()
        craneTaskRepository.downloadTask()
        inefficienciesRepository.downloadTask()
        craneInefficienciesRepository.downloadTask()
        trazabilityRepository.downloadTask()
        craneTrazabilityRepository.downloadTask()
        ots1Repository.download()
        //personelRepository.downloadPersonel()
//        craneRecolocationRepository.downloadCraneRecolocation()
//
        //resourceActivationRepository.downloadResourceActivation()


        return ListenableWorker.Result.success()
    }
}
