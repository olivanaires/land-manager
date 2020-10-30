<template>
    <b-row align-h="center">
        <b-card :header="title" header-class="header-title" class="col-md-9">
            <b-card-body>
                <ValidationObserver v-slot="{ invalid }">
                    <b-form name="form" @submit.prevent="handleRegister">

                        <b-form-row>
                            <c-input-select label-value="Contrato" v-model="igpm.contract"
                                            :option-values="contractOptions"
                                            bs-col-value="col-md-12" roles-value="required"/>
                        </b-form-row>

                        <b-form-row>
                            <c-input-date label-value="Data Aplicação" v-model="igpm.date"
                                          bs-col-value="col-md-4" roles-value="required"/>
                            <c-input-text label-value="Ano Referente" v-model="igpm.year" input-type="number"
                                          bs-col-value="col-md-4" roles-value="required"/>
                            <b-form-group label="Valor (%)" class=" col-md-4">
                                <money v-model="igpm.latepenalty" class="form-control" :required="true"></money>
                            </b-form-group>
                        </b-form-row>


                        <b-row align-h="center">
                            <b-button class="col-md-3" type="submit" :disabled="invalid" variant="success">Salvar
                            </b-button>
                            <div class="space"></div>
                            <b-button class="col-md-3" type="button" @click="refresh" variant="success">Cancelar
                            </b-button>
                        </b-row>

                    </b-form>
                </ValidationObserver>
            </b-card-body>
        </b-card>
    </b-row>
</template>

<script>
    import ContractService from '../../services/contract.service';
    import IgpmService from '../../services/igpm.service';

    export default {
        name: 'igpmRegister',
        data() {
            return {
                title: 'Aplicar IGPM',
                igpm: {},
                contractOptions: []
            };
        },
        created() {
            ContractService.list()
                .then(resp => {
                    this.contractOptions = resp.data.map(c => ({item: c, name: `${c.number} - ${c.people[0].name}`}));
                }).catch(error => this.$swal({icon: 'error', title: error.response.data.message}));
        },
        methods: {
            handleRegister(event) {
                IgpmService.apply(this.igpm)
                    .then(resp => this.$swal({icon: 'success', title: resp.data.message}))
                    .catch(error => this.$swal({icon: 'error', title: error.response.data.message}));
                event.target.reset();
            },
            refresh() {
                this.$el.getElementsByTagName("form").namedItem('form').reset()
            }
        }
    };
</script>

<style scoped>

    .card {
        padding: 0px !important;
    }

    .profile-img-card {
        width: 96px;
        height: 96px;
        margin: 0 auto 10px;
        display: block;
        -moz-border-radius: 50%;
        -webkit-border-radius: 50%;
        border-radius: 50%;
    }

    span.c-erro-msg {
        color: #cc0033;
        display: inline-block;
        font-size: 12px;
        line-height: 15px;
        margin: 0;
    }

    .header-title {
        font-size: 25px !important;
        text-align: center;
    }

    .space {
        margin: 10px;
    }
</style>