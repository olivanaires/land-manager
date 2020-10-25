<template>
    <b-row align-h="center">
        <b-card :header="title" header-class="header-title" class="col-md-12">
            <b-card-body>
                <b-card header="Filtros" class="col-md-12">
                    <b-input type="search" class="filtro" @input="filter = $event"
                             placeholder="Filtre por número contrato"/>
                </b-card>
                <br/>

                <b-pagination v-model="currentPage" :total-rows="rows"
                              :per-page="perPage" aria-controls="my-table">
                </b-pagination>
                <b-table striped hover bordered show-empty
                         :items="filteredContracts"
                         :fields="fields"
                         :per-page="perPage"
                         :current-page="currentPage"
                         empty-text="Não existe contratos a vencer esse mês"/>
            </b-card-body>
        </b-card>
    </b-row>
</template>

<script>
    import moment from 'moment';
    import ContractService from '../../services/contract.service';

    export default {
        name: 'contractList',
        data() {
            return {
                title: 'Listagem de Contratos',
                perPage: 10,
                currentPage: 1,
                filter: '',
                contracts: [],
                fields: [
                    {
                        key: 'number',
                        label: 'Número',
                        sortable: true,
                        thClass: 'bg-dark text-white'
                    },
                    {
                        key: 'contractdate',
                        label: 'Data',
                        thClass: 'bg-dark text-white',
                        formatter: "formatDate"
                    },
                    {
                        key: 'land.landLocation.name',
                        label: 'Localização',
                        sortable: true,
                        thClass: 'bg-dark text-white'
                    },
                    {
                        key: 'people',
                        label: 'Clientes',
                        sortable: true,
                        thClass: 'bg-dark text-white',
                        formatter: "formatPeople"
                    },
                ]
            }
        },
        created() {
            ContractService.list().then(
                response => {
                    this.contracts = response.data;
                });
        },
        computed: {
            rows() {
                return this.contracts.length
            },
            filteredContracts() {
                if (this.filter) {
                    let exp = new RegExp(this.filter.trim(), 'i');
                    return this.contracts.filter(c => exp.test(c.number));
                } else {
                    return this.contracts;
                }
            }
        },
        methods: {
            formatDate(value) {
                if (value) {
                    return moment(String(value), "YYYY-MM-DD").format('DD/MM/YYYY');
                } else {
                    return 'Indefinida';
                }
            },
            formatPeople(value) {
                if (value) {
                    return value.map(p => p['name']).join(', ');
                } else {
                    return 'Indefinido';
                }
            },
        }
    }
</script>

<style scoped>
    .card {
        padding: 0px !important;
        margin-bottom: 10px;
    }
    .header-title {
        font-size: 25px !important;
        text-align: center;
    }
</style>